<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/14 0014
  Time: 上午 9:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>通知详情</h1>
        标题<br/>
        ${notice.title}<br/>
        发布时间<br/>
        ${notice.issueTime}<br/>
        内容<br/>
        ${notice.text}<br/>
</body>
</html>
