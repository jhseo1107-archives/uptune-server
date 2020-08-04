package kr.kro.uptune.Servlet

import kr.kro.uptune.Data.CommentDAO
import kr.kro.uptune.Data.UserDAO
import kr.kro.uptune.Util.SendTrendReportMail
import kr.kro.uptune.Util.ReportReason
import kr.kro.uptune.Util.SendCommentReportMail
import kr.kro.uptune.Util.isCorrectSession
import org.json.simple.JSONObject
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "kr.kro.uptune.Servlet.ReportCommentServlet", value = ["/reportComment"])
class ReportCommentServlet : HttpServlet() {
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

        var commentid = req.getParameter("commentid")
        var commentreportreason = req.getParameter("reportreason")

        var commentdao = CommentDAO()
        var commentdto = commentdao.getFromCommentId(Integer.valueOf(commentid))
        commentdao.disconnect()

        var userdao = UserDAO()
        var useremail = userdao.getFromUserNo(commentdto.commentWriter).userEmail
        var commentcontent = commentdto.commentContent
        userdao.disconnect()

        SendCommentReportMail("uptune.software@gmail.com","seojanghyeob@gmail.com",Integer.valueOf(commentid), ReportReason(Integer.valueOf(commentreportreason)), useremail, commentcontent)


        jsonObject.put("status", 200)
        res.writer.print(jsonObject.toJSONString())
    }
}