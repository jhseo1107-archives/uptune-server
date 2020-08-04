package kr.kro.uptune.Servlet

import kr.kro.uptune.Data.*
import kr.kro.uptune.Util.isCorrectSession
import kr.kro.uptune.Util.sdf
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "kr.kro.uptune.Servlet.GetTrendVideoServlet", value = ["/getTrendVideo"])
class GetTrendVideoServlet : HttpServlet() {
    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        doProcess(req, res)
    }

    override fun doPost(req: HttpServletRequest, res: HttpServletResponse) {
        doProcess(req, res)
    }

    private fun doProcess(req: HttpServletRequest, res: HttpServletResponse) {
        res.contentType = "text/plain; charset=utf-8"
        req.characterEncoding = "UTF-8"

        var session = req.getSession(true)
        var jsonObject = JSONObject()


        if(!isCorrectSession(session))
        {
            jsonObject.put("status", 403)
            res.writer.print(jsonObject.toJSONString())
            return
        }
        jsonObject.put("status", 200)

        var userId = session.getAttribute("userno") as Int
        var trendId = Integer.valueOf(req.getParameter("trendid"))

        var trenddao = TrendDAO()
        var userdao = UserDAO()
        var liketrenddao = TrendLikeDAO()

        var trenddto = trenddao.getFromTrendId(trendId)
        var commentlist = trenddto.trendComments
        var commentarray = JSONArray()

        jsonObject.put("trendid", trenddto.trendId)
        jsonObject.put("trendname", trenddto.trendName)
        jsonObject.put("trendtimestamp", sdf.format(trenddto.trendTimeStamp))
        jsonObject.put("trendwriter", userdao.getFromUserNo(trenddto.trendWriter).userName)
        jsonObject.put("trendextension", trenddto.trendFileExtension)
        jsonObject.put("trendlikes", trenddto.trendLikes)
        jsonObject.put("hasliked", liketrenddao.hasLiked(userId, trenddto.trendId))

        liketrenddao.disconnect()

        for(dto in commentlist)
        {
            if(dto.commentContent == "Deleted")
                continue;

            var tempobject = JSONObject()

            tempobject.put("id", dto.commentId)
            tempobject.put("content", dto.commentContent)
            tempobject.put("timestamp", dto.commentTimeStamp)
            tempobject.put("writer", userdao.getFromUserNo(dto.commentWriter).userName)

            var replylist = dto.commentReply
            var replyarray = JSONArray()

            for(rdto in replylist)
            {
                if(rdto.commentReplyContent == "Deleted")
                    continue;
                var rtempobject = JSONObject()

                rtempobject.put("id", rdto.commentReplyId)
                rtempobject.put("content", rdto.commentReplyContent)
                rtempobject.put("timestamp", rdto.commentReplyTimestamp)
                rtempobject.put("writer", userdao.getFromUserNo(rdto.commentReplyWriter).userName)

                replyarray.add(rtempobject)
            }

            tempobject.put("reply", replyarray)

            commentarray.add(tempobject)
        }

        userdao.disconnect()
        trenddao.disconnect()

        jsonObject.put("comment", commentarray)

        res.writer.print(jsonObject.toJSONString())

    }
}