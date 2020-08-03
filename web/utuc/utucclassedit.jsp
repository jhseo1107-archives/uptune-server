<%@ page import="kr.kro.uptune.Data.ClassDTO" %>
<%@ page import="kr.kro.uptune.Data.ClassDAO" %>
<%@ page import="kr.kro.uptune.Data.ClassVideoDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="kr.kro.uptune.Data.UserDAO" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: seoja
  Date: 2020-08-03
  Time: 오후 2:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UTUC - Uptune Tutorial Upload Center</title>
</head>
<body>
<%
    int classId = Integer.valueOf(request.getParameter("classId"));
    ClassDAO classdao = new ClassDAO();
    ClassDTO classdto = classdao.getFromClassId(classId);
    UserDAO userdao = new UserDAO();
    ArrayList<ClassVideoDTO> vidlist = classdto.getClassVideo();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
%>

<h1>강좌 <%=classdto.getClassName()%> 수정하기</h1>
<%=userdao.getFromUserNo(classdto.getClassWriter()).getUserEmail()%>, <%=sdf.format(classdto.getClassTimeStamp())%><br><br>
강좌 정보 <br>
<form method="post" action="./UTUCClassEdit">
    <input type="hidden" name="classId" value="<%=classdto.getClassId()%>">
    <input type="text" name="className" maxlength="20" value="<%=classdto.getClassName()%>">
    <input type="text" name="classTimeStamp" readonly="readonly" value="<%=sdf.format(classdto.getClassTimeStamp())%>">
    <input type="text" name="classWriterDisplay" readonly="readonly" value="<%=userdao.getFromUserNo(classdto.getClassWriter()).getUserEmail()%>">
    <input type="hidden" name="classWriter" value="<%=classdto.getClassWriter()%>">
    <input type="submit" name="action" value="수정하기">
    <input type="submit" name="action" value="삭제하기">
</form>

영상 목록<br>
<%
    for(ClassVideoDTO dto : vidlist) {
        if(dto.getClassVideoName().equals("Deleted")) continue;
%>
<form method="post" action="./UTUCClassVideoEdit">
    <input type="hidden" name="classId" value="<%=classdto.getClassId()%>">
    <input type="hidden" name="classVideoId" value="<%=dto.getClassVideoId()%>">
    <input type="text" name="classVideoName" maxlength="20" value="<%=dto.getClassVideoName()%>">
    <input type="text" name="classVideoTimeStamp" readonly="readonly" value="<%= sdf.format(dto.getClassVideoTimeStamp())%>">
    <input type="text" name="classVideoWriterDisplay" readonly="readonly" value="<%=userdao.getFromUserNo(dto.getClassVideoWriter()).getUserEmail()%>">
    <input type="hidden" name="classVideoWriter" value="<%=dto.getClassVideoWriter()%>">
    <input type="text" name="classVideoLink" maxlength="20" value="<%=dto.getClassVideoLink()%>">
    <input type="submit" name="action" value="수정하기">
    <input type="submit" name="action" value="삭제하기">
</form>
<%
    }
%>
영상 추가<br>
<form method="post" action="./UTUCClassVideoAdd">
    <input type="hidden" name="classId" value="<%=classdto.getClassId()%>">
    <input type="text" name="classVideoName" maxlength="20" placeholder="영상 제목">
    <input type="text" name="classVideoLink" maxlength="20" placeholder="영상 링크">
    <input type="submit" value="추가하기">
</form>

</body>
</html>
