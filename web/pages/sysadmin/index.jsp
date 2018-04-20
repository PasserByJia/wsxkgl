
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>唯实选课管理系统</title>
</head>
<frameset rows="20%,75%,5%">
    <c:url var="headerUrl" value="/pages/sysadmin/header.jsp"></c:url>
    <c:url var="menuUrl" value="/pages/sysadmin/menu.jsp"></c:url>
    <c:url var="welcomeUrl" value="/pages/sysadmin/welcome.jsp"></c:url>
    <frame src="${headerUrl}" noresize="noresize" scrolling="no" frameborder="0"/>
    <frameset cols="20%,80%">
        <frame src="${menuUrl}" noresize="noresize" scrolling="no" frameborder="1"/>
        <frame src="${welcomeUrl}" noresize="noresize" name="main" frameborder="1"/>
    </frameset>
</frameset>
</html>