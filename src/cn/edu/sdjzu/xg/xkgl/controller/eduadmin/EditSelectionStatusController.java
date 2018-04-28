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
        //获取请求参数
        int course_id = Helper.getIdFromRequest(request);
        try {
            //获取指定id的课程对象
            Course course = CourseService.getInstance().find(course_id);
            //调换课程的状态
            if (course.isStatus()){
                course.setStatus(false);
            }else {
                course.setStatus(true);
            }
            //更新课程信息
            CourseService.getInstance().update(course);
            //请求重定向到指定路径
            response.sendRedirect("/selectionResultController");
        } catch (SQLException e) {
            //将message属性值存储到request对象中
            request.setAttribute("message","修改选课状态失败");
            //请求转发到指定路径
            request.getRequestDispatcher("/pages/error.jsp").forward(request,response);
        }

    }
}
