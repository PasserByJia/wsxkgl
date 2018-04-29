package cn.edu.sdjzu.xg.xkgl.controller.sysAdmin.teacherManage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/teacherSysadminMessageController")
public class TeacherSysadminMessageController extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //请求转发到message.jsp
        request.getRequestDispatcher("/pages/sysadmin/teacher/message.jsp").forward(request,response);
    }
}
