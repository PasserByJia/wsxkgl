<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2018/4/21
  Time: 11:42
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>

<c:url var="uploadFile" value="/teacherExcleInput"/>
<form action="${uploadFile}" method="post" enctype ="multipart/form-data">
    <input type="file" name="fileUpload" label=“上传文件"/>
    <input type="submit" value="提交">
</form>

</body>
</html>
