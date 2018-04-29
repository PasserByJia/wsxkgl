package cn.edu.sdjzu.xg.xkgl.controller.student;

import cn.edu.sdjzu.xg.xkgl.dao.CourseSelectionDao;
import cn.edu.sdjzu.xg.xkgl.domain.Course;
import cn.edu.sdjzu.xg.xkgl.domain.CourseSelection;
import cn.edu.sdjzu.xg.xkgl.domain.Student;
import cn.edu.sdjzu.xg.xkgl.service.CourseSelectionService;
import cn.edu.sdjzu.xg.xkgl.service.CourseService;
import cn.edu.sdjzu.xg.xkgl.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

//将此类声明为Servlet,selectionConntrollerAdd是该类的映射地址
@WebServlet("/selectionControllerAdd")
public class SelectionControllerAdd extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取courseId属性值
        String courseID_str = req.getParameter("courseId");
        //courseId由string类型转换为int型
        int courseID = Integer.parseInt(courseID_str);
        //声明引用
        Course course = null;
        Student student = null;
        try {
            //获取course,student对象
            course = CourseService.getInstance().find(courseID);
            student = (Student)req.getSession().getAttribute("student");
            //创建新courseSelection对象，执行增加操作
            CourseSelection courseSelectionToAdd = new CourseSelection(course, student);
            CourseSelectionService.getCourseSelectionService().add(courseSelectionToAdd);
            //重定向到selectionController
            resp.sendRedirect("selectionController");
        } catch (Exception e) {
            //异常处理，把错误信息保存在request的message属性中，请求转发到error.jsp
            req.setAttribute("message",e.getMessage());
            req.getRequestDispatcher("/pages/error.jsp").forward(req,resp);
        }
    }
}
