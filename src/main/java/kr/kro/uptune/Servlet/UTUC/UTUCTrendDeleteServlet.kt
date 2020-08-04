package kr.kro.uptune.Servlet.UTUC

import kr.kro.uptune.Data.TrendDAO
import kr.kro.uptune.Data.TrendDTO
import kr.kro.uptune.Data.UserDAO
import kr.kro.uptune.Servlet.DeleteTrendServlet
import kr.kro.uptune.Util.isCorrectSession
import kr.kro.uptune.Util.isCorrectUTUCSession
import org.json.simple.JSONObject
import java.nio.file.Files
import java.nio.file.Paths
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "kr.kro.uptune.Servlet.UTUC.UTUCTrendDeleteServlet", value = ["/utuc/UTUCTrendDelete"])
class UTUCTrendDeleteServlet : HttpServlet() {
    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        doProcess(req, res)
    }

    override fun doPost(req: HttpServletRequest, res: HttpServletResponse) {
        doProcess(req, res)
    }

    private fun doProcess(req: HttpServletRequest, res: HttpServletResponse) {
        res.contentType = "text/plain; charset=utf-8"
        req.characterEncoding = "UTF-8"

        var session = req.getSession(true)
        var jsonObject = JSONObject()

        if(!isCorrectUTUCSession(session))
        {
            jsonObject.put("status", 403)
            res.writer.print(jsonObject.toJSONString())
            return
        }

        var trenddao = TrendDAO()
        var trenddto = TrendDTO()

        var trendid = req.getParameter("trendid")

        trenddto =  trenddao.getFromTrendId(Integer.valueOf(trendid))


        trenddto.trendName = "Deleted"
        trenddao.update(trenddto)

        trenddao.disconnect()

        Files.deleteIfExists(Paths.get(servletContext.getRealPath("Videos/")+trenddto.trendId+"."+trenddto.trendFileExtension))

        jsonObject.put("status", 200)
        res.writer.print(jsonObject.toJSONString())

    }
}