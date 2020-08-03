package kr.kro.uptune.Servlet

import kr.kro.uptune.Data.CommentDAO
import kr.kro.uptune.Data.CommentDTO
import kr.kro.uptune.Util.isCorrectSession
import org.json.simple.JSONObject
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "kr.kro.uptune.Servlet.DeleteCommentServlet", value = ["/deleteComment"])
class DeleteCommentServlet : HttpServlet() {
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

        var commentdao = CommentDAO()
        var commentdto = CommentDTO()

        var commentid = req.getParameter("commentid")

        commentdto =  commentdao.getFromCommentId(Integer.valueOf(commentid))

        if(commentdto.commentWriter != session.getAttribute("userno"))
        {
            jsonObject.put("status", 403)
            res.writer.print(jsonObject.toJSONString())
            return
        }

        commentdto.commentContent = "Deleted"
        commentdao.update(commentdto)

        commentdao.disconnect()

        jsonObject.put("status", 200)
        res.writer.print(jsonObject.toJSONString())
        return
    }
}