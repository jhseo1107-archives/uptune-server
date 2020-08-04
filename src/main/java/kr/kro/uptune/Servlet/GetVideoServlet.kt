package kr.kro.uptune.Servlet

import kr.kro.uptune.Data.ClassDAO
import kr.kro.uptune.Data.ClassVideoDAO
import kr.kro.uptune.Data.ClassVideoViewDAO
import kr.kro.uptune.Data.UserDAO
import kr.kro.uptune.Util.isCorrectSession
import kr.kro.uptune.Util.sdf
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "kr.kro.uptune.Servlet.GetVideoServlet", value = ["/getVideo"])
class GetVideoServlet : HttpServlet() {
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
        var videoId = Integer.valueOf(req.getParameter("videoid"))

        var videodao = ClassVideoDAO()
        var userdao = UserDAO()
        var classvideoviewdao = ClassVideoViewDAO()

        var videodto = videodao.getFromClassVideoId(videoId)
        var commentlist = videodto.classVideoComment
        var commentarray = JSONArray()

        jsonObject.put("videoid", videodto.classVideoId)
        jsonObject.put("videoname", videodto.classVideoName)
        jsonObject.put("videotimestamp", sdf.format(videodto.classVideoTimeStamp))
        jsonObject.put("videowriter", userdao.getFromUserNo(videodto.classVideoWriter).userName)
        jsonObject.put("videolink", videodto.classVideoLink)

        classvideoviewdao.write(userId, videoId)

        classvideoviewdao.disconnect()

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
        videodao.disconnect()

        jsonObject.put("comment", commentarray)

        res.writer.print(jsonObject.toJSONString())
    }
}