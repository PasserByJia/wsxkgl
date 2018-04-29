package cn.edu.sdjzu.xg.xkgl.controller.student;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/studentMessageController")
public class StudentMesssageController extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //请求转发到studentMessage.jsp
        response.sendRedirect("/pages/student/menu/studentMessage.jsp");
    }
}
