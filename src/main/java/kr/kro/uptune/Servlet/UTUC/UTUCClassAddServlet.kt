package kr.kro.uptune.Servlet.UTUC

import kr.kro.uptune.Data.ClassDAO
import kr.kro.uptune.Data.ClassDTO
import kr.kro.uptune.Data.UserDAO
import kr.kro.uptune.Util.isCorrectUTUCSession
import kr.kro.uptune.Util.sdf
import java.sql.Timestamp
import java.util.*
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "kr.kro.uptune.Servlet.UTUC.UTUCClassAddServlet", value = ["/utuc/UTUCClassAdd"])
class UTUCClassAddServlet : HttpServlet() {
    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        doProcess(req, res)
    }

    override fun doPost(req: HttpServletRequest, res: HttpServletResponse) {
        doProcess(req, res)
    }

    private fun doProcess(req: HttpServletRequest, res: HttpServletResponse) {
        res.contentType = "text/plain; charset=utf-8"

        var classname = req.getParameter("className")

        if(!isCorrectUTUCSession(req.getSession(true)))
        {
            res.writer.print("HTTP CODE 403 - 세션이 없습니다.")
            return;
        }

        var classdao = ClassDAO()
        var classdto = ClassDTO()
        var userdao = UserDAO()

        var date = Date()


        classdto.classId = classdao.count() + 1
        classdto.className = classname
        classdto.classWriter = userdao.getFromUserEmail(req.getSession(true).getAttribute("mail") as String?).userNo
        classdto.classTimeStamp = Timestamp(System.currentTimeMillis())

        classdao.write(classdto)

    }
}