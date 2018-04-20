package cn.edu.sdjzu.xg.xkgl.controller.eduadmin;

import cn.edu.sdjzu.xg.xkgl.domain.Course;
import cn.edu.sdjzu.xg.xkgl.domain.CourseSelection;
import cn.edu.sdjzu.xg.xkgl.domain.Student;
import cn.edu.sdjzu.xg.xkgl.service.CourseSelectionService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addSelectionCourse")
public class AddSelectionCourse extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        Student student = (Student)httpSession.getAttribute("student");
        Course course = (Course)httpSession.getAttribute("course");
        CourseSelection courseSelection = new CourseSelection(course,student);
        try {
            CourseSelectionService.getCourseSelectionService().add(courseSelection);
        } catch (Exception e) {
            req.setAttribute("message",e.getMessage());
            req.getRequestDispatcher("/pages/error.jsp").forward(req,resp);
        }
        resp.sendRedirect("/selectionResultController");
    }
}
