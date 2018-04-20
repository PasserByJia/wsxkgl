<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
</head>
<%--
<frameset rows="20%,75%,5%">
    &lt;%&ndash;构造Url地址&ndash;%&gt;
    <c:url var="headerUrl" value="/pages/eduadmin/header.jsp"/>
    <c:url var="menuUrl" value="menu.jsp"/>
    <c:url var="welcomeUrl" value="welcome.jsp"/>
    &lt;%&ndash;头部&ndash;%&gt;
    <frame src="${headerUrl}" noresize="noresize" scrolling="no" frameborder="0"/>
    <frameset cols="15%,85%">
        &lt;%&ndash;菜单&ndash;%&gt;
        <frame src="${menuUrl}" noresize="noresize" frameborder="1"/>
        &lt;%&ndash;内容区域&ndash;%&gt;
        <frame src="${welcomeUrl}" noresize="noresize" name="main" frameborder="1"/>
    </frameset>
</frameset>
--%>


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
