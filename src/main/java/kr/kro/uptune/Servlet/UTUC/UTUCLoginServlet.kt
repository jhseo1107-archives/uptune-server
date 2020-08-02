package kr.kro.uptune.Servlet.UTUC

import kr.kro.jhseo1107.EncryptBuilder1107
import kr.kro.jhseo1107.EncryptMethod
import kr.kro.uptune.Data.UserDAO
import kr.kro.uptune.Data.UserDTO
import org.json.simple.JSONObject
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "kr.kro.jhseo1107.Servlet.UTUCLoginServlet", value = ["/utuc/UTUCLogin"])
class UTUCLoginServlet : HttpServlet() {
    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        doProcess(req, res)
    }

    override fun doPost(req: HttpServletRequest, res: HttpServletResponse) {
        doProcess(req, res)
    }

    private fun doProcess(req: HttpServletRequest, res: HttpServletResponse) {
        res.contentType = "text/plain; charset=utf-8"

        var temsess = req.getSession(true)
        temsess.invalidate()

        var mail = req.getParameter("userID")
        var rawpw = req.getParameter("userPW")

        var eb = EncryptBuilder1107()
        var hashedpw = eb.setPlainText(rawpw).setEncryptMethod(EncryptMethod.SHA_256).build()

        var userdao = UserDAO()

        var userdto = UserDTO()

        userdto = userdao.getFromUserEmail(mail)

        var serversidepw = userdto.userPassword

        if(serversidepw == hashedpw && userdto.userIsAdmin)
        {
            var session = req.getSession(true)
            session.setAttribute("type","utuc")
            session.setAttribute("mail", mail)
            res.sendRedirect("./utucedit.jsp")
        }
        else
        {
            res.writer.print("HTTP CODE 403 - 권한 부족. 올바른 비밀번호를 입력했는지, 혹시 어드민 등록이 안되지는 않았는지 다시 한번 확인해 주세요. 문의 : seojanghyeob@gmail.com")
        }
    }
}