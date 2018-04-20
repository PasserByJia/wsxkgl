/*
* 课程通过teacher    findall
* */
package cn.edu.sdjzu.xg.xkgl.controller.teacher;

import cn.edu.sdjzu.xg.xkgl.domain.*;
import cn.edu.sdjzu.xg.xkgl.service.CourseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.TreeSet;

@WebServlet("/teacherResultsController")
public class  ResultsController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       try {
           Teacher teacher = (Teacher) req.getSession().getAttribute("teacher");
           Collection<Course> coursesToReturn = CourseService.getInstance().findByTeacher(teacher);
           req.setAttribute("coursesToReturn",coursesToReturn);
       }catch (SQLException e){
           req.setAttribute("message","查询选课结果失败");
           req.getRequestDispatcher("/pages/error.jsp").forward(req,resp);
       }
       req.getRequestDispatcher("/pages/teacher/selectionResults.jsp").forward(req,resp);
    }
}

