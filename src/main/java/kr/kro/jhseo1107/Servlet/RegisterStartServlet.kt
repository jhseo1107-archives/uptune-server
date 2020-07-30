package kr.kro.jhseo1107.Servlet

import kr.kro.jhseo1107.Data.UserDAO
import kr.kro.jhseo1107.EncryptBuilder1107
import kr.kro.jhseo1107.EncryptMethod
import kr.kro.jhseo1107.Util.SendMail
import org.json.simple.JSONObject
import java.util.*
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "kr.kro.jhseo1107.Servlet.RegisterStartServlet", value = ["/registerStart"])
class RegisterStartServlet : HttpServlet() {
    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        doProcess(req, res)
    }

    override fun doPost(req: HttpServletRequest, res: HttpServletResponse) {
        doProcess(req, res)
    }

    private fun doProcess(req: HttpServletRequest, res: HttpServletResponse) {
        res.contentType = "text/plain; charset=utf-8"

        req.getSession(true).invalidate()

        var mail = req.getParameter("mail")
        var rawpw = req.getParameter("pw")
        var username = req.getParameter("usrname")

        var eb = EncryptBuilder1107()
        var hashedpw = eb.setPlainText(rawpw).setEncryptMethod(EncryptMethod.SHA_256).build()

        var random = Random()
        var rannum = Integer.toString(random.nextInt(900000) + 100000)

        var jsonobj = JSONObject()

        var userdao = UserDAO()
        if (userdao.existFromUserEmail(mail)) // if email exists on db
        {
            jsonobj.put("status", 409);
            res.writer.print(jsonobj.toJSONString())
            return;
        }
        userdao.disconnect()

        var session = req.getSession(true)
        session.maxInactiveInterval = 300

        session.setAttribute("type", "register")
        session.setAttribute("mail", mail)
        session.setAttribute("pw", hashedpw)
        session.setAttribute("auth", rannum)
        session.setAttribute("usrname", username)

        res.writer.print(rannum) // Debug Purposes
        SendMail("uptune.software@gmail.com", mail, rannum);
    }
}