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
        //获取请求参数
        String courseTitle = req.getParameter("courseTitle");
        //创建集合对象，存储选课记录
        Collection<CourseSelection> courseSelectionCollection = new HashSet<CourseSelection>();
        try {
            //根据传入的参数获取符合条件的CourseSelection集合对象，并存储到指定集合对象中
            courseSelectionCollection = CourseSelectionService.getCourseSelectionService().findByCourseTitle(courseTitle);
            //将courseSelections属性的属性值courseSelections存储到request对象中
            req.setAttribute("courseSelections",courseSelectionCollection);
            //请求转发到指定页面
            req.getRequestDispatcher("/pages/eduadmin/selection/selectionResult.jsp").forward(req,resp);
        } catch (SQLException e) {
            //将message属性的属性值存储到request对象中
            req.setAttribute("message","选课结果查询出错");
            //请求转发到指定页面
            req.getRequestDispatcher("/pages/error.jsp").forward(req,resp);
        }


    }
}
