package cn.edu.sdjzu.xg.xkgl.controller.eduadmin;

import cn.edu.sdjzu.xg.xkgl.dao.CourseTypeDao;
import cn.edu.sdjzu.xg.xkgl.domain.Course;
import cn.edu.sdjzu.xg.xkgl.domain.CourseSelection;
import cn.edu.sdjzu.xg.xkgl.domain.CourseType;
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
import java.util.Collection;
import java.util.HashSet;

@WebServlet("/selectionResultController")
public class SelectionResultController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
        Collection<Course> courses = null;
        try {
            courses = CourseService.getInstance().findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("courses",courses);
        request.getRequestDispatcher("/pages/eduadmin/selection/selectionResult.jsp").forward(request,response);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            Collection<CourseType>  courseTypes = CourseTypeDao.getInstance().findAll();
            req.setAttribute("courseTypes",courseTypes);
            StringBuilder conditionSb =  new StringBuilder();
            String title = req.getParameter("title");
            conditionSb.append(" title like '%").append(title).append("%' ");
            Collection<Course> courses = CourseService.getInstance().findAll(conditionSb.toString());
            req.setAttribute("courses",courses);
            req.getRequestDispatcher("/pages/eduadmin/selection/selectionResult.jsp").forward(req,resp);
        }catch (SQLException e) {
            req.setAttribute("message","查询失败");
            req.getRequestDispatcher("/pages/error.jsp").forward(req,resp);
        }
    }
}
