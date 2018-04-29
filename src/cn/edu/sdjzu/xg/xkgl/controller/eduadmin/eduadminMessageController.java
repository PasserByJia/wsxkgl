package cn.edu.sdjzu.xg.xkgl.controller.eduadmin;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/eduadminMessageController")
public class eduadminMessageController extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //重定向到指定页面
        response.sendRedirect("/pages/student/menu/eduadminMessage.jsp");
    }
}
