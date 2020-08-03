package kr.kro.uptune.Servlet

import kr.kro.uptune.Data.TrendDAO
import kr.kro.uptune.Data.TrendDTO
import kr.kro.uptune.Util.TomcatProperties
import kr.kro.uptune.Util.isCorrectSession
import org.json.simple.JSONObject
import java.nio.file.Files
import java.nio.file.Paths
import java.sql.Timestamp
import javax.servlet.annotation.MultipartConfig
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.Part

@WebServlet(name = "kr.kro.uptune.Servlet.WriteTrendServlet", value = ["/writeTrend"])
@MultipartConfig
class WriteTrendServlet : HttpServlet() {
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

        if(!isCorrectSession(session))
        {
            jsonObject.put("status", 403)
            res.writer.print(jsonObject.toJSONString())
            return
        }

        var trenddao = TrendDAO()
        var trenddto = TrendDTO()

        trenddto.trendId = trenddao.count()+1
        trenddto.trendWriter = session.getAttribute("userno") as Int
        trenddto.trendLikes = 0
        //trenddto.trendName = session.getAttribute("lastTrendName") as String
        trenddto.trendName = req.getParameter("trendname")
        trenddto.trendTimeStamp = Timestamp(System.currentTimeMillis())

        trenddao.write(trenddto)

        var filePart = req.getPart("file")
        var fileName = Paths.get(filePart.submittedFileName).fileName.toString()
        var fileNameArray = fileName.toString().split(".")
        var fileContent = filePart.inputStream
        Files.copy(fileContent, Paths.get(servletContext.getRealPath("Videos/")+trenddto.trendId+"."+fileNameArray[1]));

    }
}