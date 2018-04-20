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

@WebServlet("/findSelectionResultController")
public class FindSelectionResultController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String courseTitle = req.getParameter("courseTitle");
        Collection<CourseSelection> courseSelectionCollection = new HashSet<CourseSelection>();
        try {
            courseSelectionCollection = CourseSelectionService.getCourseSelectionService().findByCourseTitle(courseTitle);
        } catch (SQLException e) {
            req.setAttribute("message","选课结果查询出错");
            req.getRequestDispatcher("/pages/error.jsp").forward(req,resp);
        }
        req.setAttribute("courseSelections",courseSelectionCollection);
        req.getRequestDispatcher("/pages/eduadmin/selection/selectionResult.jsp").forward(req,resp);

    }
}
