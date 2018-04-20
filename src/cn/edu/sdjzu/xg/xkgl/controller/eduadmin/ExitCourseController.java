package cn.edu.sdjzu.xg.xkgl.controller.eduadmin;

import cn.edu.sdjzu.xg.xkgl.domain.CourseSelection;
import cn.edu.sdjzu.xg.xkgl.service.CourseSelectionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;

@WebServlet("/exitCourseController")
public class ExitCourseController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentUsername = req.getParameter("studentid");
        Collection<CourseSelection> courseSelectionCollection = new HashSet<CourseSelection>();
        try {
            courseSelectionCollection = CourseSelectionService.getCourseSelectionService().findByStudentUsername(studentUsername);
        } catch (SQLException e) {
            req.setAttribute("message","查询退选学生请确认学号是否输入正确");
            req.getRequestDispatcher("/pages/error.jsp").forward(req,resp);
        }
        req.setAttribute("courseSelections",courseSelectionCollection);
        req.getRequestDispatcher("/pages/eduadmin/selection/ExitCourse.jsp").forward(req,resp);
    }
}
