package kr.kro.uptune.Servlet.UTUC

import kr.kro.uptune.Data.CommentReplyDAO
import kr.kro.uptune.Data.CommentReplyDTO
import kr.kro.uptune.Util.isCorrectUTUCSession
import org.json.simple.JSONObject
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "kr.kro.uptune.Servlet.UTUC.UTUCCommentReplyDeleteServlet", value = ["/utuc/UTUCCommentReplyDelete"])
class UTUCCommentReplyDeleteServlet : HttpServlet() {
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

        if(!isCorrectUTUCSession(session))
        {
            jsonObject.put("status", 403)
            res.writer.print(jsonObject.toJSONString())
            return
        }

        var commentreplydao = CommentReplyDAO()
        var commentreplydto = CommentReplyDTO()

        var commentreplyid = req.getParameter("commentreplyid")

        commentreplydto =  commentreplydao.getFromCommentReplyId(Integer.valueOf(commentreplyid))


        commentreplydto.commentReplyContent = "Deleted"
        commentreplydao.update(commentreplydto)

        commentreplydao.disconnect()

        jsonObject.put("status", 200)
        res.writer.print(jsonObject.toJSONString())
    }
}