package cn.edu.sdjzu.xg.xkgl.controller.eduadmin;

import cn.edu.sdjzu.xg.xkgl.domain.Course;
import cn.edu.sdjzu.xg.xkgl.domain.Student;
import cn.edu.sdjzu.xg.xkgl.service.CourseService;
import cn.edu.sdjzu.xg.xkgl.service.StudentService;
import util.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;

@WebServlet("/findCourseController")
public class FindCourseController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //创建集合对象
        Collection<Course> courses = new HashSet<Course>();
        try{
            //获得所有课程，并存储到指定的集合对象中
            courses = CourseService.getInstance().findAll();
            //将course属性的属性值courses存储到request对象中
            req.setAttribute("course",courses);
            //请求重定向到指定页面
            req.getRequestDispatcher("/pages/eduadmin/selection/addCourseForStudent/findCourse.jsp").forward(req,resp);
        }catch(SQLException e){
            //将message属性的属性值存储到request对象中
            req.setAttribute("message","获取课程失败");
            //请求转发到指定页面
            req.getRequestDispatcher("/pages/error.jsp").forward(req,resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        String studentNo = req.getParameter("studentNo");
        int id = Helper.getIdFromRequest(req,"courseId");
        Student student = null;
        Course course = null;
        try {
            //根据指定的参数获取相应的Student对象
            student = StudentService.getInstance().findByUsername(studentNo);
            course =  CourseService.getInstance().find(id);
            //创建会话对象
            HttpSession httpSession = req.getSession();
            //向会话域中存入属性student 的属性值student
            httpSession.setAttribute("student",student);
            httpSession.setAttribute("course",course);
            req.setAttribute("course",course);
            req.setAttribute("student",student);
            //请求转发到指定页面
            req.getRequestDispatcher("/pages/eduadmin/selection/addCourseForStudent/addCourseForStudent.jsp").forward(req,resp);
        } catch (SQLException e) {
            //将message属性的属性值存储到request对象中
            req.setAttribute("message","添加选课失败");
            //请求转发到指定页面
            req.getRequestDispatcher("/pages/error.jsp").forward(req,resp);
        }


    }
}
