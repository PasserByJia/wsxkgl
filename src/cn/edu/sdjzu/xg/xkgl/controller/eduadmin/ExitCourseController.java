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

@WebServlet("/exitCourseController")
public class ExitCourseController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        String studentUsername = req.getParameter("studentid");
        //创建集合对象
        Collection<CourseSelection> courseSelectionCollection = new HashSet<CourseSelection>();
        try {
            //获取CourseSelectionService实例，调用相应方法获取所有的CourseSelection对象,并存储到相应的集合对象中
            courseSelectionCollection = CourseSelectionService.getCourseSelectionService().findByStudentUsername(studentUsername);
            //将courseSelections属性的属性值courseSelectionCollection存储到request对象中
            req.setAttribute("courseSelections",courseSelectionCollection);
            //请求转发到指定路径
            req.getRequestDispatcher("/pages/eduadmin/selection/ExitCourse.jsp").forward(req,resp);
        } catch (SQLException e) {
            //将message属性的属性值存储到request对象中
            req.setAttribute("message","查询退选学生请确认学号是否输入正确");
            //请求重定向到指定路径
            req.getRequestDispatcher("/pages/error.jsp").forward(req,resp);
        }

    }
}
