package cn.edu.sdjzu.xg.xkgl.controller.student;

import cn.edu.sdjzu.xg.xkgl.dao.CourseSelectionDao;
import cn.edu.sdjzu.xg.xkgl.domain.Course;
import cn.edu.sdjzu.xg.xkgl.domain.CourseSelection;
import cn.edu.sdjzu.xg.xkgl.domain.Student;
import cn.edu.sdjzu.xg.xkgl.service.CourseSelectionService;
import cn.edu.sdjzu.xg.xkgl.service.CourseService;
import cn.edu.sdjzu.xg.xkgl.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

@WebServlet("/selectionControllerAdd")
public class SelectionControllerAdd extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String courseID_str = req.getParameter("courseId");
        int courseID = Integer.parseInt(courseID_str);
        //创建CourseSelection对象
        Course course = null;
        Student student = null;
        try {
            course = CourseService.getInstance().find(courseID);
            student = (Student)req.getSession().getAttribute("student");
            CourseSelection courseSelectionToAdd = new CourseSelection(course, student);
            CourseSelectionService.getCourseSelectionService().add(courseSelectionToAdd);
            resp.sendRedirect("selectionController");
        } catch (Exception e) {
            req.setAttribute("message",e.getMessage());
            req.getRequestDispatcher("/pages/error.jsp").forward(req,resp);
        }
    }
}
