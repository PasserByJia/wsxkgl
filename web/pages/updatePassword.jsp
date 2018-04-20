<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/14 0014
  Time: 上午 7:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>修改密码</title>
</head>
<body>
<h1>修改密码</h1>
    <c:url var="updatepasswordUrl" value="/updatepassword"/>
    <form action="${updatepasswordUrl}" method="post">
        密码
        <input type="text" name="password1"/>
        <br/>
        再次输入
        <input type="text" name="password2"/>
        <br/>
        ${msg}
        <br>
        <input type="submit"/>
    </form>
</body>
</html>
