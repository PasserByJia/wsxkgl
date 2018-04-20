<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="textml;charset=UTF-8">
    <title></title>
</head>
<frameset rows="20%,75%,5%">
    <c:url value="/imgs/header.jpg" var="headerUrl"/>
    <c:url value="menu.jsp" var="menuUrl"/>
    <c:url value="welcome.jsp" var="welcomeUrl"/>
    <frame src="${headerUrl}" noresize="noresize" frameborder="0" scrolling="no"/>
    <frameset cols ="15%,85%">
        <frame src="${menuUrl}" noresize="noresize" frameborder="1"/>
        <frame src="${welcomeUrl}" noresize="noresize" name="main" frameborder="1"/>
    </frameset>
</frameset>
</html>
