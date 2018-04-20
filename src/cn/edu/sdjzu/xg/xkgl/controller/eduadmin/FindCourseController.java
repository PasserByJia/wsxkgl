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
        Collection<Course> courses = new HashSet<Course>();
        try{
            courses = CourseService.getInstance().findAll();
        }catch(SQLException e){
            req.setAttribute("message","获取课程失败");
            req.getRequestDispatcher("/pages/error.jsp").forward(req,resp);
        }
        req.setAttribute("course",courses);
        req.getRequestDispatcher("/pages/eduadmin/selection/addCourseForStudent/findCourse.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentNo = req.getParameter("studentNo");
        int id = Helper.getIdFromRequest(req,"courseId");
        Student student = null;
        Course course = null;
        try {
            student = StudentService.getInstance().findByUsername(studentNo);
            course =  CourseService.getInstance().find(id);
        } catch (SQLException e) {
            req.setAttribute("message","添加选课失败");
            req.getRequestDispatcher("/pages/error.jsp").forward(req,resp);
        }
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("student",student);
        httpSession.setAttribute("course",course);
        req.setAttribute("course",course);
        req.setAttribute("student",student);
        req.getRequestDispatcher("/pages/eduadmin/selection/addCourseForStudent/addCourseForStudent.jsp").forward(req,resp);

    }
}
