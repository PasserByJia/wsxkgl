<%--
  Created by IntelliJ IDEA.
  User: liuyang
  Date: 2018/4/14
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Menu</title>
</head>
<body>
<br/>
${teacher.name}  &nbsp;&nbsp;&nbsp;&nbsp;
<c:url var="logoutUrl" value="/logout" />
<a href="${logoutUrl}" target="_top">安全退出</a>
<br/>
<c:url var="NoticeControllerUrl" value="/noticeController"/>
<a href="${NoticeControllerUrl}" target="main">查看通知</a>
<br/>
<c:url var="ResultsControllerUrl" value="/teacherResultsController"/>
<a href="${ResultsControllerUrl}" target="main">查看选课结果</a>
<br/>
<c:url var="updatePassword" value="/pages/updatePassword.jsp"/>
<a href="${updatePassword}" target="main">修改密码</a>
<br/>
</body>
</html>
