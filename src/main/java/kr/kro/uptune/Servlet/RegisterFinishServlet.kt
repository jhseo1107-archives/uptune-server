package kr.kro.uptune.Servlet

import kr.kro.uptune.Data.UserDAO
import kr.kro.uptune.Data.UserDTO
import org.json.simple.JSONObject
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "kr.kro.jhseo1107.Servlet.RegisterFinishServlet", value = ["/registerFinish"])
class RegisterFinishServlet : HttpServlet() {
    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        doProcess(req, res)
    }

    override fun doPost(req: HttpServletRequest, res: HttpServletResponse) {
        doProcess(req, res)
    }

    private fun doProcess(req: HttpServletRequest, res: HttpServletResponse) {
        res.contentType = "text/plain; charset=utf-8"

        var authfromuser = req.getParameter("auth")

        var authfromsession = req.getSession().getAttribute("auth")

        var jsonobj = JSONObject()


        if (authfromuser == authfromsession) {
            var userdto = UserDTO()
            var userdao = UserDAO()

            userdto.userNo = userdao.count() + 1
            userdto.userEmail = req.getSession().getAttribute("mail") as String
            userdto.userName = req.getSession().getAttribute("usrname") as String
            userdto.userPassword = req.getSession().getAttribute("pw") as String

            userdao.write(userdto)
            userdao.disconnect()

            jsonobj.put("status", 200)
        } else {
            jsonobj.put("status", 403)
        }

        var returnstring = jsonobj.toJSONString()

        res.writer.print(returnstring)
    }
}