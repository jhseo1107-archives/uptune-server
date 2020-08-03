package kr.kro.uptune.Servlet

import kr.kro.uptune.Data.CommentDAO
import kr.kro.uptune.Data.CommentDTO
import kr.kro.uptune.Data.UserDAO
import kr.kro.uptune.Util.isCorrectSession
import org.json.simple.JSONObject
import java.sql.Timestamp
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "kr.kro.uptune.Servlet.WriteCommentServlet", value = ["/WriteComment"])
class WriteCommentServlet : HttpServlet() {
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


        if(isCorrectSession(session))
        {
            jsonObject.put("status", 403)
            res.writer.print(jsonObject.toJSONString())
            return
        }

        var commentdao = CommentDAO()
        var commentdto = CommentDTO()

        var userNo = Integer.valueOf(session.getAttribute("userno") as String)

        var commentContent = req.getParameter("content")
        var commentParent = Integer.valueOf(req.getParameter("parent"))
        var commentParentType = Integer.valueOf(req.getParameter("parenttype"))

        commentdto.commentId = commentdao.count() + 1
        commentdto.commentContent = commentContent
        commentdto.commentParentId = commentParent
        commentdto.commentParentType = commentParentType
        commentdto.commentWriter = userNo
        commentdto.commentTimeStamp = Timestamp(System.currentTimeMillis())

        commentdao.write(commentdto)
        commentdao.disconnect()

        jsonObject.put("status", 200)

    }
}