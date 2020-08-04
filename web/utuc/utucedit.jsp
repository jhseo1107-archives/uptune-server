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
        if(classdto.getClassName().equals("Deleted")) continue;
%>
<form method="get" action="./utucclassedit.jsp">
    <input type="hidden" name="classId" value="<%= classdto.getClassId()%>">
    <input type="submit" value="<%= classdto.getClassName() %>">
</form>
<%
    }
%>
강좌 추가<br>
<form method="post" action="./UTUCClassAdd">
    <input type="text" placeholder="강좌 이름" name="className" maxlength="100">
    <input type="submit" value="Add">
</form>

트렌드 영상 삭제 (이용약관 위반의 경우)<br>
<form method="post" action="./UTUCTrendDelete">
    <input type="text" placeholder="Trend ID" name="trendid">
    <input type="submit" value="Delete">
</form>
유저 삭제 (이용약관 위반의 경우)<br>
<form method="post" action="./UTUCUserDelete">
    <input type="text" placeholder="User E-mail" name="useremail">
    <input type="submit" value="Delete">
</form>
댓글 삭제 (이용약관 위반의 경우)<br>
<form method="post" action="./UTUCCommentDelete">
    <input type="text" placeholder="Comment ID" name="commentid">
    <input type="submit" value="Delete">
</form>
답글 삭제 (이용약관 위반의 경우)<br>
<form method="post" action="./UTUCCommentReplyDelete">
    <input type="text" placeholder="CommentReply ID" name="commentreplyid">
    <input type="submit" value="Delete">
</form>

</body>
</html>
