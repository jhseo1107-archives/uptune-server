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

@WebServlet(name = "kr.kro.jhseo1107.Servlet.GetMainServlet", value = ["/getMain"])
class GetMainServlet : HttpServlet() {
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

        var userId = session.getAttribute("userno") as Int

        // top three percentage class

        var classarray = JSONArray()

        var classdao = ClassDAO()
        var classlist = classdao.all
        classdao.disconnect()

        var classvideoviewdao = ClassVideoViewDAO()

        var pairlist = ArrayList<Pair<ClassDTO, Int>>()

        for(dto in classlist)
        {
            pairlist.add(Pair(dto, classvideoviewdao.viewPercentage(userId, dto.classId)))
        }

        classvideoviewdao.disconnect()

        pairlist.sortBy { it.second }

        for(i in pairlist.lastIndex downTo 0)
        {
            if(i > pairlist.lastIndex-3)
            {
                if(pairlist.get(i).first.className == "Deleted")
                    continue
                var tempobj = JSONObject()

                tempobj.put("id", pairlist.get(i).first.classId)
                tempobj.put("name", pairlist.get(i).first.className)
                tempobj.put("percentage", pairlist.get(i).second)

                classarray.add(tempobj)
            }
        }

        //Trends : recursive fun in order to stop deleted trends being shown.
        var trendarray = JSONArray()

        var trenddao = TrendDAO()
        var userdao = UserDAO()

        for(dto in getTopTrend(trenddao, 3))
        {
            var tempobj = JSONObject()

            tempobj.put("id", dto.trendId)
            tempobj.put("name", dto.trendName)
            tempobj.put("timestamp", sdf.format(dto.trendTimeStamp))
            tempobj.put("likes", dto.trendLikes)

            trendarray.add(tempobj)
        }

        trenddao.disconnect()
        userdao.disconnect()

        jsonObject.put("class", classarray)
        jsonObject.put("trend", trendarray)
        
        
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
            countedflags += (flag-countedflags)
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