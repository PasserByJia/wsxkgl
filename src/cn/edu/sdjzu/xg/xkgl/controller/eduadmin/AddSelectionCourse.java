package cn.edu.sdjzu.xg.xkgl.controller.eduadmin;

import cn.edu.sdjzu.xg.xkgl.domain.Course;
import cn.edu.sdjzu.xg.xkgl.domain.CourseSelection;
import cn.edu.sdjzu.xg.xkgl.domain.Student;
import cn.edu.sdjzu.xg.xkgl.service.CourseSelectionService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addSelectionCourse")
public class AddSelectionCourse extends HttpServlet {
    @Override
    //doPost方法
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取会话对象
        HttpSession httpSession = req.getSession();
        //通过属性名"student"从会话域中获取属性值
        Student student = (Student)httpSession.getAttribute("student");
        Course course = (Course)httpSession.getAttribute("course");
        //创建CourseSelection对象
        CourseSelection courseSelection = new CourseSelection(course,student);
        try {
            //获取CourseSelectionService实例，调用其add方法，增加一个选课记录
            CourseSelectionService.getCourseSelectionService().add(courseSelection);
            //重定向到指定页面
            resp.sendRedirect("/selectionResultController");
        } catch (Exception e) {
            //将message属性值e.getMessage()获取到的异常信息存储到request对象中
            req.setAttribute("message",e.getMessage());
            //请求转发到error页面
            req.getRequestDispatcher("/pages/error.jsp").forward(req,resp);
        }

    }
}
