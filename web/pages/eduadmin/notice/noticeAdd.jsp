<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/4/15
  Time: 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add</title>
</head>
<body>
<h1>添加通知</h1>
<c:url var="addUrl" value="/noticeControllerAdd"/>
<form action="${addUrl}" method="post">
    <table>
        <thead>

        <td>标题</td>
        <td>内容</td>

        </thead>
        <tr>
            <td><input type="text" name="title" /></td>
            <td><input type="text" name="text" /></td>

        <tr/>
        <div align="center">
            <input type="submit" value="确定" />
        </div>
    </table>
</form>
</body>
</html>
