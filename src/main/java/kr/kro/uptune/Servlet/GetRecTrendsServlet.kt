package kr.kro.uptune.Servlet

import kr.kro.uptune.Data.TrendDAO
import kr.kro.uptune.Data.TrendDTO
import kr.kro.uptune.Data.UserDAO
import kr.kro.uptune.Util.isCorrectSession
import kr.kro.uptune.Util.sdf
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "kr.kro.uptune.Servlet.GetRecTrendsServlet", value = ["/getRecTrends"])
class GetRecTrendsServlet : HttpServlet() {
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
        jsonObject.put("status", 200)

        var page = Integer.valueOf(req.getParameter("page"))

        var trenddao = TrendDAO()
        var rectrendlist = trenddao.getRecent(page*10, 10)
        trenddao.disconnect()

        var userdao = UserDAO()

        var rectrendarray = JSONArray()
        for(dto in rectrendlist)
        {
            var tempobject = JSONObject()

            tempobject.put("id", dto.trendId)
            tempobject.put("name", dto.trendName)
            tempobject.put("writer", userdao.getFromUserNo(dto.trendWriter).userName)
            tempobject.put("timestamp", sdf.format(dto.trendTimeStamp))
            tempobject.put("likes", dto.trendLikes)

            rectrendarray.add(tempobject)
        }

        jsonObject.put("rectrend", rectrendarray)

        userdao.disconnect()

        res.writer.print(jsonObject.toJSONString())
    }
}
