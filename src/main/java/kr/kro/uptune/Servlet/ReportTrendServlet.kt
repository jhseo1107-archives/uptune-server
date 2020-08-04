package kr.kro.uptune.Servlet

import kr.kro.uptune.Data.TrendDAO
import kr.kro.uptune.Data.UserDAO
import kr.kro.uptune.Util.SendMail
import kr.kro.uptune.Util.SendReportMail
import kr.kro.uptune.Util.TrendReportReason
import kr.kro.uptune.Util.isCorrectSession
import org.json.simple.JSONObject
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "kr.kro.uptune.Servlet.ReportTrendServlet", value = ["/reportTrend"])
class ReportTrendServlet : HttpServlet() {
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

        var trendid = req.getParameter("trendid")
        var trendreportreason = req.getParameter("reportreason")

        var trenddao = TrendDAO()
        var trenddto = trenddao.getFromTrendId(Integer.valueOf(trendid))
        var trendfileextension = trenddto.trendFileExtension
        trenddao.disconnect()

        var userdao = UserDAO()
        var useremail = userdao.getFromUserNo(trenddto.trendWriter).userEmail
        userdao.disconnect()

        SendReportMail("uptune.software@gmail.com","seojanghyeob@gmail.com",Integer.valueOf(trendid),trendfileextension, TrendReportReason(Integer.valueOf(trendreportreason)), useremail)


        jsonObject.put("status", 200)
        res.writer.print(jsonObject.toJSONString())
    }
}