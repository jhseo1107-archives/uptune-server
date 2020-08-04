package kr.kro.uptune.Servlet

import kr.kro.uptune.Data.TrendDAO
import kr.kro.uptune.Data.TrendLikeDAO
import kr.kro.uptune.Util.isCorrectSession
import org.json.simple.JSONObject
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "kr.kro.uptune.Servlet.LikeTrendServlet", value = ["/likeTrend"])
class LikeTrendServlet : HttpServlet() {
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

        var trendid = req.getParameter("trendid")

        var trenddao = TrendDAO()
        var trenddto = trenddao.getFromTrendId(Integer.valueOf(trendid))

        var trendlikedao = TrendLikeDAO()

        if(trendlikedao.hasLiked(session.getAttribute("userno") as Int, Integer.valueOf(trendid)))
        {
            jsonObject.put("status", 400)
            res.writer.print(jsonObject.toJSONString())
            return
        }

        trenddto.trendLikes += 1
        trenddao.update(trenddto)

        trendlikedao.write(session.getAttribute("userno") as Int, Integer.valueOf(trendid))

        trenddao.disconnect()
        trendlikedao.disconnect()

        jsonObject.put("status", 200)
        res.writer.print(jsonObject.toJSONString())
        return

    }
}