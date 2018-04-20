<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${eduadmin.name}教务管理员
<c:url var="logoutUrl" value="/logout" />
<a href="${logoutUrl}" target="_top">安全退出</a>
<br/>
       <c:url var="courseControllerUrl" value="/courseController" />
           <a href="${courseControllerUrl}"  target="main">课程管理</a>
        <br/>
       <c:url value="/noticeControllerEdu" var="noticeControllerUrl"/>
           <a href="${noticeControllerUrl}" target="main">通知管理</a>
       <br/>

       <c:url value="/selectionTime" var="selectionTimeControllerUrl"/>
           <a href="${selectionTimeControllerUrl}" target="main">设置选课时间</a>
       <br/>

       <c:url value="/selectionResultController" var="selectionResultControllerUrl"/>
           <a href="${selectionResultControllerUrl}" target="main">选课结果管理</a>
       <br/>

       <c:url value="/noticeController" var="viewNotificationUrl"/>
           <a href="${viewNotificationUrl}" target="main">查看通知</a>
       <br/>

       <c:url value="/pages/updatePassword.jsp" var="updatePassword"/>
           <a href="${updatePassword}"  target="main" >修改密码</a>
</body>
</html>
