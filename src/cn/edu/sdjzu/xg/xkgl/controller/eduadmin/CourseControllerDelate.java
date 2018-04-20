package cn.edu.sdjzu.xg.xkgl.controller.eduadmin;

import cn.edu.sdjzu.xg.xkgl.service.CourseService;
import util.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/courseControllerDelate")
public class CourseControllerDelate  extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Helper.getIdFromRequest(request);
        try {
            CourseService.getInstance().delete(id);
        } catch (Exception e) {
            request.setAttribute("message",e.getMessage());
            request.getRequestDispatcher("/pages/error.jsp").forward(request,response);
        }
        response.sendRedirect("/courseController");
    }
}
