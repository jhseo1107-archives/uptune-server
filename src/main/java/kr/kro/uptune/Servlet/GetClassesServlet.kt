package kr.kro.uptune.Servlet

import kr.kro.uptune.Data.ClassDAO
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

@WebServlet(name = "kr.kro.uptune.Servlet.GetClassesServlet", value = ["/getClasses"])
class GetClassesServlet : HttpServlet() {
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

        var classdao = ClassDAO()
        var classlist = classdao.all

        var userdao = UserDAO()

        var classvideoviewdao = ClassVideoViewDAO()

        var classarray = JSONArray()

        for(dto in classlist)
        {
            if(dto.className == "Deleted")
                continue;

            var tempobject = JSONObject()

            tempobject.put("id", dto.classId)
            tempobject.put("name", dto.className)
            tempobject.put("timestamp", sdf.format(dto.classTimeStamp))
            tempobject.put("writer",userdao.getFromUserNo(dto.classWriter).userName)
            tempobject.put("percentage", classvideoviewdao.viewPercentage(userId, dto.classId))

            classarray.add(tempobject)
        }

        jsonObject.put("class", classarray)

        res.writer.print(jsonObject.toJSONString())
    }
}