package kr.kro.uptune.Servlet

import kr.kro.jhseo1107.EncryptBuilder1107
import kr.kro.jhseo1107.EncryptMethod
import kr.kro.uptune.Data.UserDAO
import org.json.simple.JSONObject
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "kr.kro.jhseo1107.Servlet.UTUCLoginServlet", value = ["/UTUCLogin"])
class UTUCLoginServlet : HttpServlet() {
    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        doProcess(req, res)
    }

    override fun doPost(req: HttpServletRequest, res: HttpServletResponse) {
        doProcess(req, res)
    }

    private fun doProcess(req: HttpServletRequest, res: HttpServletResponse) {
        res.contentType = "text/plain; charset=utf-8"

        var mail = req.getParameter("userID")
        var rawpw = req.getParameter("userPW")

        var eb = EncryptBuilder1107()
        var hashedpw = eb.setPlainText(rawpw).setEncryptMethod(EncryptMethod.SHA_256).build()

        var userdao = UserDAO()

        var serversidepw = userdao.getFromUserEmail(mail).userPassword

        if(serversidepw == hashedpw)
        {
            req.getSession(true).invalidate()
            var session = req.getSession(true)
            session.setAttribute("type","utuc")
            session.setAttribute("mail", mail)
        }
        else
        {
            var jsonObject = JSONObject()
            jsonObject.put("status", "403")
            res.writer.print(jsonObject.toJSONString())
        }
    }
}