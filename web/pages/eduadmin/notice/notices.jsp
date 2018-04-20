<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2018/4/14
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>通知</title>
</head>
<body>
<h1>通知管理</h1>
<c:url var="add" value="/pages/eduadmin/notice/noticeAdd.jsp"/>
<a href="${add}">新建</a>
<br>
<table border="1">
    <thead>
    <td>序号</td>
    <td>标题</td>
    <td>内容</td>
    <td>发布时间</td>
    <td>发布人</td>
    <td colspan="2">操作</td>
    </thead>
    <c:forEach var="notice" items="${notices}" varStatus="status" >
        <tr>
            <td>${status.index+1}</td>
            <td>${notice.title}</td>
            <td>${notice.text}</td>
            <td>${notice.issueTime}</td>
            <td>${notice.eduAdmin.name}</td>
            <td>
                <c:url var="selectUrl" value="/checknoticeController?id=${notice.id}"/>
                <a href="${selectUrl}">查看</a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
