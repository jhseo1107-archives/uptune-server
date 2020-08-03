package kr.kro.uptune.Servlet

import kr.kro.uptune.Data.CommentDAO
import kr.kro.uptune.Data.CommentDTO
import kr.kro.uptune.Data.CommentReplyDAO
import kr.kro.uptune.Data.CommentReplyDTO
import kr.kro.uptune.Util.isCorrectSession
import org.json.simple.JSONObject
import java.sql.Timestamp
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "kr.kro.uptune.Servlet.WriteCommentReplyServlet", value = ["/writeCommentReply"])
class WriteCommentReplyServlet : HttpServlet() {
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

        var commentreplydao = CommentReplyDAO()
        var commentreplydto = CommentReplyDTO()

        var userNo = session.getAttribute("userno") as Int

        var commentContent = req.getParameter("content")
        var commentParent = Integer.valueOf(req.getParameter("parent"))

        commentreplydto.commentReplyId = commentreplydao.count() + 1
        commentreplydto.commentReplyContent = commentContent
        commentreplydto.commentReplyParentId = commentParent
        commentreplydto.commentReplyWriter = userNo
        commentreplydto.commentReplyTimestamp = Timestamp(System.currentTimeMillis())

        commentreplydao.write(commentreplydto)
        commentreplydao.disconnect()

        jsonObject.put("status", 200)
    }
}