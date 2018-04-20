package cn.edu.sdjzu.xg.xkgl.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Administrator on 2017/11/13.
 */
//201602104029 贾昊
@WebServlet("/logout")
public class LogoutController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("teacher");
        session.removeAttribute("student");
        session.removeAttribute("sysadmin");
        session.removeAttribute("eduadmin");
        response.sendRedirect("loginController");
        session.invalidate();
    }
}
