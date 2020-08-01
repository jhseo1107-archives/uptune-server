<%--
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
Add admin user...<br>
<form method="post" action="./UTUCAdminAdd">
    <input type="text" placeholder="ID" name="userID" maxlength="25">
    <input type="submit" value="Add">
</form>
</body>
</html>
