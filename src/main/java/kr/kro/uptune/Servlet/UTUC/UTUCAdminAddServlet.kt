package kr.kro.uptune.Servlet.UTUC

import kr.kro.uptune.Data.UserDAO
import kr.kro.uptune.Util.isCorrectUTUCSession
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "kr.kro.uptune.Servlet.UTUC.UTUCAdminAddServlet", value = ["/utuc/UTUCAdminAdd"])
class UTUCAdminAddServlet : HttpServlet() {
    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        doProcess(req, res)
    }

    override fun doPost(req: HttpServletRequest, res: HttpServletResponse) {
        doProcess(req, res)
    }

    private fun doProcess(req: HttpServletRequest, res: HttpServletResponse) {
        res.contentType = "text/plain; charset=utf-8"

        var mail = req.getParameter("userID")

        if(!isCorrectUTUCSession(req.getSession(true)))
        {
            res.writer.print("HTTP CODE 403 - 세션이 없습니다.")
            return;
        }
        var userdao = UserDAO()
        userdao.editToAdmin(mail)
    }
}