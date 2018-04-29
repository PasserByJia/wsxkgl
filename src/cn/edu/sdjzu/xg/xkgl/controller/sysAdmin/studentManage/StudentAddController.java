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

//将此类声明为Servlet,studentConntroller是该类的映射地址
@WebServlet("/studentAddController")
public class StudentAddController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //获取请求参数的各个属性值
            String name = request.getParameter("name");
            String no =  request.getParameter("no");
            String sex = request.getParameter("sex");
            //属性值赋给创建的新Student对象，
            Student studentToAdd = new Student(no,"123456",sex,no,name);
            //执行增加操作
            StudentService.getInstance().add(studentToAdd);
            //重定向到studentController
            response.sendRedirect("studentController");
        } catch (SQLException e) {
            //异常处理，把错误信息保存在request的message属性中，请求转发到error.jsp
            request.setAttribute("message","添加学生失败");
            request.getRequestDispatcher("/pages/error.jsp").forward(request,response);
        }
    }
}
