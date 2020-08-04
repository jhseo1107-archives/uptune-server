package kr.kro.uptune.Servlet

import kr.kro.uptune.Data.ClassDAO
import kr.kro.uptune.Data.ClassVideoDAO
import kr.kro.uptune.Data.ClassVideoViewDAO
import kr.kro.uptune.Data.UserDAO
import kr.kro.uptune.Util.isCorrectSession
import kr.kro.uptune.Util.sdf
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "kr.kro.uptune.Servlet.GetClassVideosServlet", value = ["/getClassVideos"])
class GetClassVideosServlet : HttpServlet() {
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
        var classId = Integer.valueOf(req.getParameter("classid"))

        var classdao = ClassDAO()
        var userdao = UserDAO()
        var classvideoviewdao = ClassVideoViewDAO()

        var classdto = classdao.getFromClassId(classId)
        var classvideolist = classdto.classVideo

        var classvideoarray = JSONArray()

        jsonObject.put("classid", classdto.classId)
        jsonObject.put("classname", classdto.className)
        jsonObject.put("classtimestamp", sdf.format(classdto.classTimeStamp))
        jsonObject.put("classwriter", userdao.getFromUserNo(classdto.classWriter).userName)
        jsonObject.put("classpercentage", classvideoviewdao.viewPercentage(userId, classId))

        classdao.disconnect()

        for(dto in classvideolist)
        {
            if(dto.classVideoName == "Deleted")
                continue;

            var tempobject = JSONObject()

            tempobject.put("id", dto.classVideoId)
            tempobject.put("name", dto.classVideoName)
            tempobject.put("hasviewed", classvideoviewdao.hasViewed(userId, dto.classVideoId))

            classvideoarray.add(tempobject)
        }

        userdao.disconnect()
        classvideoviewdao.disconnect()

        jsonObject.put("videos", classvideoarray)

        res.writer.print(jsonObject.toJSONString())
    }
}