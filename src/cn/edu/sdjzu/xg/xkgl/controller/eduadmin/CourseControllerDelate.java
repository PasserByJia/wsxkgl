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
        //获取请求参数，并存储到相应变量中
        int id = Helper.getIdFromRequest(request);
        try {
            //删除与id相同的课程对象
            CourseService.getInstance().delete(id);
            //重定向到指定页面
            response.sendRedirect("/courseController");
        } catch (Exception e) {
            //设置message属性的相应属性值并存储到request对象中
            request.setAttribute("message",e.getMessage());
            //请求转发到指定页面
            request.getRequestDispatcher("/pages/error.jsp").forward(request,response);
        }

    }
}
