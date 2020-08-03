package kr.kro.uptune.Servlet.UTUC

import kr.kro.uptune.Data.ClassDAO
import kr.kro.uptune.Util.isCorrectUTUCSession
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "kr.kro.uptune.Servlet.UTUC.UTUCClassEditServlet", value = ["/utuc/UTUCClassEdit"])
class UTUCClassEditServlet : HttpServlet() {
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
        var className = req.getParameter("className")
        var action = req.getParameter("action")

        var classdao = ClassDAO()
        var dto = classdao.getFromClassId(Integer.valueOf(classId))

        if(action == "수정하기")
        {
            dto.className = className
            classdao.update(dto)
            res.writer.print("edit")
            res.sendRedirect("./utucclassedit.jsp?classId="+dto.classId)
        }
        else if(action =="삭제하기")
        {
            dto.className = "Deleted"
            classdao.update(dto)
            res.writer.print("delete")
            res.sendRedirect("./utucedit.jsp")
        }

    }
}