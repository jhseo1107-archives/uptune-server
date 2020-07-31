package kr.kro.jhseo1107.Servlet

import kr.kro.jhseo1107.Util.isCorrectSession
import org.json.simple.JSONObject
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "kr.kro.jhseo1107.Servlet.LogoutServlet", value = ["/logout"])
class LogoutServlet : HttpServlet() {
    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        doProcess(req, res)
    }

    override fun doPost(req: HttpServletRequest, res: HttpServletResponse) {
        doProcess(req, res)
    }

    private fun doProcess(req: HttpServletRequest, res: HttpServletResponse) {
        res.contentType = "text/plain; charset=utf-8"

        var session = req.getSession(true)
        var jsonobject = JSONObject()

        if(isCorrectSession(session))
        {
            jsonobject.put("status", 400)
            res.writer.print(jsonobject.toJSONString())
            return
        }

        session.invalidate()
        jsonobject.put("status", 200)
        res.writer.print(jsonobject.toJSONString())
    }
}