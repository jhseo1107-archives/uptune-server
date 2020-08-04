package kr.kro.uptune.Servlet

import kr.kro.uptune.Data.CommentReplyDAO
import kr.kro.uptune.Data.UserDAO
import kr.kro.uptune.Util.ReportReason
import kr.kro.uptune.Util.SendCommentReplyReportMail
import kr.kro.uptune.Util.isCorrectSession
import org.json.simple.JSONObject
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "kr.kro.uptune.Servlet.ReportCommentReplyServlet", value = ["/reportCommentReply"])
class ReportCommentReplyServlet : HttpServlet() {
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

        var commentreplyid = req.getParameter("commentreplyid")
        var commentreplyreportreason = req.getParameter("reportreason")

        var commentreplydao = CommentReplyDAO()
        var commentreplydto = commentreplydao.getFromCommentReplyId(Integer.valueOf(commentreplyid))
        commentreplydao.disconnect()

        var userdao = UserDAO()
        var useremail = userdao.getFromUserNo(commentreplydto.commentReplyWriter).userEmail
        userdao.disconnect()

        SendCommentReplyReportMail("uptune.software@gmail.com","seojanghyeob@gmail.com",Integer.valueOf(commentreplyid), ReportReason(Integer.valueOf(commentreplyreportreason)), useremail)


        jsonObject.put("status", 200)
        res.writer.print(jsonObject.toJSONString())
    }
}