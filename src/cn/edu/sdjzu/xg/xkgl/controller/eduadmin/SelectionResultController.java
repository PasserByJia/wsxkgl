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
            //获取所有课程，并存储到相应的集合对象中
            courses = CourseService.getInstance().findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //将courses属性的属性值courses存储到request对象中
        request.setAttribute("courses",courses);
        //请求转发到指定页面
        request.getRequestDispatcher("/pages/eduadmin/selection/selectionResult.jsp").forward(request,response);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            //获取所有课程种类，并存储到集合对象courseTypes中
            Collection<CourseType>  courseTypes = CourseTypeDao.getInstance().findAll();
            //将courseType属性的属性值courseType存储到request对象中
            req.setAttribute("courseTypes",courseTypes);
            //创建StringBuilder对象
            StringBuilder conditionSb =  new StringBuilder();
            //追加sql目标SQL语句
            String title = req.getParameter("title");
            conditionSb.append(" title like '%").append(title).append("%' ");
            Collection<Course> courses = CourseService.getInstance().findAll(conditionSb.toString());
            //将courses属性的属性值courses存储到request对象中
            req.setAttribute("courses",courses);
            //请求转发到指定页面
            req.getRequestDispatcher("/pages/eduadmin/selection/selectionResult.jsp").forward(req,resp);
        }catch (SQLException e) {
            //将错误信息存储到request对象中
            req.setAttribute("message","查询失败");
            //请求转发到指定页面
            req.getRequestDispatcher("/pages/error.jsp").forward(req,resp);
        }
    }
}
