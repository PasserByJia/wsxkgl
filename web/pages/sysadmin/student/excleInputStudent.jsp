<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2018/4/22
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:url var="uploadFile" value="/studentExcleInput"/>
<form action="${uploadFile}" method="post" enctype ="multipart/form-data">
    <input type="file" name="fileUpload" label=“上传文件"/>
    <input type="submit" value="提交">
</form>

</body>
</html>
