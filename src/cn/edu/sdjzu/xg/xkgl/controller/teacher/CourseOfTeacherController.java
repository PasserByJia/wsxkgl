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

//将此类声明为Servlet,courseOfTeacherConntroller是该类的映射地址
@WebServlet("/courseOfTeacherController")
public class CourseOfTeacherController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //从session中获取Teacher对象
            Teacher teacher = (Teacher) req.getSession().getAttribute("teacher");
            //获取当前教师用户关联的所有课程保存在Collection集合中
            Collection<Course> coursesOfTeacher = CourseService.getInstance().findByTeacher(teacher);
            //设定coursesOfTeacher属性的值并保存在request范围内
            req.setAttribute("coursesOfTeacher",coursesOfTeacher);
            //请求转发到selectionResults.jsp
            req.getRequestDispatcher("/pages/teacher/selectionResults.jsp").forward(req,resp);
        }catch (SQLException e){
            //异常处理，把错误信息保存在request的message属性中，请求转发到error.jsp
            req.setAttribute("message","查询课程结果失败");
            req.getRequestDispatcher("/pages/error.jsp").forward(req,resp);
        }

    }
}

