package kr.kro.jhseo1107.Servlet

import kr.kro.jhseo1107.Data.UserDAO
import kr.kro.jhseo1107.EncryptBuilder1107
import kr.kro.jhseo1107.EncryptMethod
import org.json.simple.JSONObject
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "kr.kro.jhseo1107.Servlet.LoginServlet", value = ["/login"])
class LoginServlet : HttpServlet() {
    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        doProcess(req, res)
    }

    override fun doPost(req: HttpServletRequest, res: HttpServletResponse) {
        doProcess(req, res)
    }

    private fun doProcess(req: HttpServletRequest, res: HttpServletResponse) {
        res.contentType = "text/plain; charset=utf-8"
        req.getSession(true).invalidate()

        var jsonobject = JSONObject()

        var mail = req.getParameter("mail")
        var rawpw = req.getParameter("pw")

        var eb = EncryptBuilder1107()
        var hashedpw = eb.setPlainText(rawpw).setEncryptMethod(EncryptMethod.SHA_256).build()

        var userdao = UserDAO()

        if(!userdao.existFromUserEmail(mail))
        {
            jsonobject.put("status", 400)
        }

        var userdto = userdao.getFromUserEmail(mail)
        userdao.disconnect()

        if(userdto?.userPassword == hashedpw)
        {
            jsonobject.put("status", 200)
            var session = req.getSession(true)
            session.setAttribute("type", "login")
            session.setAttribute("id", mail)
            session.setAttribute("username", userdto.userName)
            session.setAttribute("userno", userdto.userNo)
        }
        else
        {
            jsonobject.put("status", 403)
            jsonobject.put("userpassword", hashedpw)
            jsonobject.put("dbuserno", userdto?.userNo)
            jsonobject.put("dbusermail", userdto?.userEmail)
            jsonobject.put("dbusername", userdto?.userName)
            jsonobject.put("dbpassword", userdto?.userPassword)
        }

        res.writer.print(jsonobject.toJSONString())
    }
}