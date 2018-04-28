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
        //设置请求参数
        int courseSelectionId = Helper.getIdFromRequest(req,"courseSelectionId");
        try {
            //根据参数，删除相应id的选课记录
            CourseSelectionService.getCourseSelectionService().delete(courseSelectionId);
            //请求重定向到指定路径
            req.getRequestDispatcher("/pages/eduadmin/selection/ExitCourse.jsp").forward(req,resp);

        } catch (Exception e) {
            //将message属性值存储到request对象中
            req.setAttribute("message",e.getMessage());
            //请求转发到指定路径
            req.getRequestDispatcher("/pages/error.jsp").forward(req,resp);
        }

    }
}
