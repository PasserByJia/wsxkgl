<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/14
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>增加教师</h1>
<c:url var="addUrl" value="/teacherAddController"/>
<form action="${addUrl}" method="post">
    姓名
    <input type="text" name="name" />
    <br/>
    工号
    <input type="text" name="no"/>
    <br />
    性别
    <input type="radio" name="sex" value="男" checked>男
    <input type="radio" name="sex" value="女">女
    <br />
    职称
    <select name="profTitle">
        <c:forEach var="profTitle" items="${profTitleSet}">
            <option value="${profTitle.id}">${profTitle.description}</option>
        </c:forEach>
    </select>
    <br />
    <input type="submit"/>
</form>
</body>
</html>
