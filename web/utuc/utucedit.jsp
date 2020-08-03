<%@ page import="kr.kro.uptune.Data.ClassDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="kr.kro.uptune.Data.ClassDAO" %>
<%@ page import="java.util.Iterator" %><%--
  Created by IntelliJ IDEA.
  User: seoja
  Date: 2020-08-01
  Time: 오후 6:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UTUC - Uptune Tutorial Upload Center</title>
</head>
<body>
<% request.setCharacterEncoding("UTF-8"); %>
<%
ClassDAO classdao = new ClassDAO();
ArrayList<ClassDTO> classlist = classdao.getAll();
%>


Add admin user...<br>
<form method="post" action="./UTUCAdminAdd">
    <input type="text" placeholder="ID" name="userID" maxlength="25">
    <input type="submit" value="Add">
</form>
강좌 목록<br>
<%
    for(ClassDTO classdto : classlist) {
%>
<form method="get" action="./utucclassedit.jsp">
    <input type="hidden" name="classId" value=<%= classdto.getClassId()%>>
    <input type="submit" value=<%= classdto.getClassName() %>>
</form>
<%
    }
%>
강좌 추가<br>
<form method="post" action="./UTUCClassAdd">
    <input type="text" placeholder="강좌 이름" name="className" maxlength="20">
    <input type="submit" value="Add">
</form>

</body>
</html>
