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
                &nbsp;&nbsp;&nbsp;&nbsp;开始时间&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="text" value="beginYear" name="beginYear">年
                    <input type="text" value="beginMonth" name="beginMonth">月
                    <input type="text" value="beginDay" name="beginDay">日
                    <input type="text" value="beginHour" name="beginHour">时
                    <input type="text" value="beginMinute" name="beginMinute">分
            <br>
                &nbsp;&nbsp;&nbsp;&nbsp;结束时间&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="text" value="endYear" name="endYear">年
                    <input type="text" value="endMonth" name="endMonth">月
                    <input type="text" value="endDay" name="endDay">日
                    <input type="text" value="endHour" name="endHour">时
                    <input type="text" value="endMinute" name="endMinute">分
            <br>

        <button>
            <input type="submit"  value="确定" />
        </button>
    </form>
</body>
</html>
