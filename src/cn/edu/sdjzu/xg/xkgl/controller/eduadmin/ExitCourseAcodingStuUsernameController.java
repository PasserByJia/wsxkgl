package cn.edu.sdjzu.xg.xkgl.controller.eduadmin;

import cn.edu.sdjzu.xg.xkgl.domain.CourseSelection;
import cn.edu.sdjzu.xg.xkgl.service.CourseSelectionService;
import util.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

@WebServlet("/exitCourseAcodingStuUsernameController")
public class ExitCourseAcodingStuUsernameController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int courseSelectionId = Helper.getIdFromRequest(req,"courseSelectionId");
        try {
            CourseSelectionService.getCourseSelectionService().delete(courseSelectionId);
        } catch (Exception e) {
            req.setAttribute("message",e.getMessage());
            req.getRequestDispatcher("/pages/error.jsp").forward(req,resp);
        }
        req.getRequestDispatcher("/pages/eduadmin/selection/ExitCourse.jsp").forward(req,resp);

    }
}
