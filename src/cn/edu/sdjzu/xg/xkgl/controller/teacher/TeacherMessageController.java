package cn.edu.sdjzu.xg.xkgl.controller.teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//将此类声明为Servlet,teacherMessageConntroller是该类的映射地址
@WebServlet("/teacherMessageController")
public class TeacherMessageController extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //请求转发到teacherMessage.jsp
        request.getRequestDispatcher("/pages/teacher/menu/teacherMessage.jsp").forward(request,response);
    }
}
