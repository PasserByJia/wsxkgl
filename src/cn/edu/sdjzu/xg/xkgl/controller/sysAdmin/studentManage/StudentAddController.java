package cn.edu.sdjzu.xg.xkgl.controller.sysAdmin.studentManage;

import cn.edu.sdjzu.xg.xkgl.domain.Student;
import cn.edu.sdjzu.xg.xkgl.service.StudentService;
import util.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/studentAddController")
public class StudentAddController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            String no =  request.getParameter("no");
            String sex = request.getParameter("sex");
            Student studentToAdd = new Student(no,"123456",sex,no,name);
            StudentService.getInstance().add(studentToAdd);
            response.sendRedirect("studentController");
        } catch (SQLException e) {
            request.setAttribute("message","添加学生失败");
            request.getRequestDispatcher("/pages/error.jsp").forward(request,response);
        }
    }
}
