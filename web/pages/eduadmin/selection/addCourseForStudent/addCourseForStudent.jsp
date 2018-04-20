<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/15 0015
  Time: 下午 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>确认补选信息</h1>
<c:url var="find" value="/addSelectionCourse" />
<form action="${find}" method="post">
    学生学号:&nbsp;${student.username} 学生姓名:&nbsp;${student.name}
    <br>
    补选课名称:&nbsp;${course.title} 课程编号:&nbsp;${course.no}
    <br>
    <button>
        <a href="/pages/eduadmin/selection/addCourseForStudent/findCourse.jsp">取消</a>
    </button>
    <button>
        <input type="submit" value="确定"/>
    </button>
</form>
</body>
</html>
