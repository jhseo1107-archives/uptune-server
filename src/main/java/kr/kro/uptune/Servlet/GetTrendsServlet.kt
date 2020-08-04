package kr.kro.uptune.Servlet

import kr.kro.uptune.Data.*
import kr.kro.uptune.Util.isCorrectSession
import kr.kro.uptune.Util.sdf
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "kr.kro.uptune.Servlet.GetTrendsServlet", value = ["/getTrends"])
class GetTrendsServlet : HttpServlet() {
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

        var trenddao = TrendDAO()
        var toptrendlist = getTopTrend(trenddao, 4)
        var rectrendlist = getRecTrend(trenddao, 4)
        trenddao.disconnect()

        var userdao = UserDAO()

        var toptrendarray = JSONArray()
        for(dto in toptrendlist)
        {
            var tempobject = JSONObject()

            tempobject.put("id", dto.trendId)
            tempobject.put("name", dto.trendName)
            tempobject.put("writer", userdao.getFromUserNo(dto.trendWriter).userName)
            tempobject.put("timestamp", sdf.format(dto.trendTimeStamp))
            tempobject.put("likes", dto.trendLikes)

            toptrendarray.add(tempobject)
        }

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

        jsonObject.put("toptrend", toptrendarray)
        jsonObject.put("rectrend", rectrendarray)

        userdao.disconnect()

        res.writer.print(jsonObject.toJSONString())
    }

    var countedflags = 0
    private fun getTopTrend(dao : TrendDAO, num: Int) : ArrayList<TrendDTO>
    {
        var trendlist = dao.getHighLikes(num)
        var returnlist = ArrayList<TrendDTO>()

        var flag = 0

        for(dto in trendlist)
        {
            if(dto.trendName == "Deleted")
                flag++;
        }

        if(flag - countedflags > 0)
        {
            countedflags = flag
            return getTopTrend(dao, num+flag)
        }

        for(dto in trendlist)
        {
            if(dto.trendName == "Deleted")
                continue;

            returnlist.add(dto)
        }

        return returnlist;
    }

    var countedflags2 = 0;
    private fun getRecTrend(dao : TrendDAO, num: Int) : ArrayList<TrendDTO>
    {
        var trendlist = dao.getRecent(0, num)
        var returnlist = ArrayList<TrendDTO>()

        var flag = 0

        for(dto in trendlist)
        {
            if(dto.trendName == "Deleted")
                flag++;
        }

        if(flag - countedflags2 > 0)
        {
            countedflags2 = flag
            return getTopTrend(dao, num+flag)
        }

        for(dto in trendlist)
        {
            if(dto.trendName == "Deleted")
                continue;

            returnlist.add(dto)
        }

        return returnlist;
    }
}