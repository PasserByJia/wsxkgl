package cn.edu.sdjzu.xg.xkgl.controller.eduadmin;

import cn.edu.sdjzu.xg.xkgl.domain.Course;
import cn.edu.sdjzu.xg.xkgl.domain.CourseSelection;
import cn.edu.sdjzu.xg.xkgl.service.CourseSelectionService;
import cn.edu.sdjzu.xg.xkgl.service.CourseService;
import util.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/editSelectionStatusController")
public class EditSelectionStatusController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int course_id = Helper.getIdFromRequest(request);
        try {
            Course course = CourseService.getInstance().find(course_id);
            if (course.isStatus()){
                course.setStatus(false);
            }else {
                course.setStatus(true);
            }
            CourseService.getInstance().update(course);
        } catch (SQLException e) {
            request.setAttribute("message","修改选课状态失败");
            request.getRequestDispatcher("/pages/error.jsp").forward(request,response);
        }

        response.sendRedirect("/selectionResultController");
    }


}
