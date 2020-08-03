<%@ page import="kr.kro.uptune.Data.ClassDTO" %>
<%@ page import="kr.kro.uptune.Data.ClassDAO" %>
<%@ page import="kr.kro.uptune.Data.ClassVideoDTO" %>
<%@ page import="java.util.ArrayList" %><%--
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
    ArrayList<ClassVideoDTO> vidlist = classdto.getClassVideo();
%>

<h1>강좌 <%=classdto.getClassName()%> 수정하기</h1>
강의 목록
<%
    for(ClassVideoDTO dto : vidlist) {
%>

<%
    }
%>

</body>
</html>
