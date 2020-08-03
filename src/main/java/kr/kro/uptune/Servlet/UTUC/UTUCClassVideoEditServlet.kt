package kr.kro.uptune.Servlet.UTUC

import kr.kro.uptune.Data.ClassDAO
import kr.kro.uptune.Data.ClassVideoDAO
import kr.kro.uptune.Util.isCorrectUTUCSession
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "kr.kro.uptune.Servlet.UTUC.UTUCClassVideoEditServlet", value = ["/utuc/UTUCClassVideoEdit"])
class UTUCClassVideoEditServlet : HttpServlet() {
    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        doProcess(req, res)
    }

    override fun doPost(req: HttpServletRequest, res: HttpServletResponse) {
        doProcess(req, res)
    }

    private fun doProcess(req: HttpServletRequest, res: HttpServletResponse) {
        res.contentType = "text/plain; charset=utf-8"
        req.characterEncoding = "UTF-8"

        if(!isCorrectUTUCSession(req.getSession(true)))
        {
            res.writer.print("HTTP CODE 403 - 세션이 없습니다.")
            return;
        }

        var classId = req.getParameter("classId")
        var classVideoId = req.getParameter("classVideoId")
        var classVideoName = req.getParameter("classVideoName")
        var classVideoLink = req.getParameter("classVideoLink")
        var action = req.getParameter("action")

        var classvideodao = ClassVideoDAO()
        var dto = classvideodao.getFromClassVideoId(Integer.valueOf(classVideoId))

        if(action == "수정하기")
        {
            dto.classVideoName = classVideoName
            classvideodao.update(dto)
            res.writer.print("edit")
            res.sendRedirect("./utucclassedit.jsp?classId="+classId)
        }
        else if(action =="삭제하기")
        {
            dto.classVideoName = "Deleted"
            classvideodao.update(dto)
            res.writer.print("delete")
            res.sendRedirect("./utucclassedit.jsp?classId="+classId)
        }
        classvideodao.disconnect()
    }
}