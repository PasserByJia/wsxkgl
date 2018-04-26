<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>设置选课时间</h1>
    <c:url value="/selectionTime" var="selectionTimeUrl" />
    <form action="${selectionTimeUrl}" method="post">

            <br>
                开始时间: <input type="date" name="startDate" /><input type="text" name="startHour" />:<input type="text" name="startMin" />
            <br>
                结束时间: <input type="date" name="endDate" /><input type="text" name="endHour" />:<input type="text" name="endMin" />
            <br>

        <button>
            <input type="submit"  value="确定" />
        </button>
    </form>
</body>
</html>
