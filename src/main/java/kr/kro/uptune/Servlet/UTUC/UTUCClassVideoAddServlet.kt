package kr.kro.uptune.Servlet.UTUC

import kr.kro.uptune.Data.*
import kr.kro.uptune.Util.isCorrectUTUCSession
import java.sql.Timestamp
import java.util.*
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "kr.kro.uptune.Servlet.UTUC.UTUCClassVideoAddServlet", value = ["/utuc/UTUCClassVideoAdd"])
class UTUCClassVideoAddServlet : HttpServlet() {
    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        doProcess(req, res)
    }

    override fun doPost(req: HttpServletRequest, res: HttpServletResponse) {
        doProcess(req, res)
    }

    private fun doProcess(req: HttpServletRequest, res: HttpServletResponse) {
        res.contentType = "text/plain; charset=utf-8"
        req.characterEncoding = "UTF-8"

        var classvideoname = req.getParameter("classVideoName")
        var classvideolink = req.getParameter("classVideoLink")
        var parentid = req.getParameter("classId")

        if(!isCorrectUTUCSession(req.getSession(true)))
        {
            res.writer.print("HTTP CODE 403 - 세션이 없습니다.")
            return;
        }

        var videodao = ClassVideoDAO()
        var videodto = ClassVideoDTO()
        var userdao = UserDAO()

        videodto.classVideoId = videodao.count() + 1
        videodto.classVideoName = classvideoname
        videodto.classVideoWriter = userdao.getFromUserEmail(req.getSession(true).getAttribute("mail") as String?).userNo
        videodto.classVideoTimeStamp = Timestamp(System.currentTimeMillis())
        videodto.classVideoLink = classvideolink
        videodto.classVideoParentId = Integer.valueOf(parentid)

        videodao.write(videodto)

        res.sendRedirect("./utucclassedit.jsp?classId="+parentid)
    }
}