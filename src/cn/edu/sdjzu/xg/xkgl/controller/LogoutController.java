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
//将此类声明为Servlet,logout是该类的映射地址
@WebServlet("/logout")
public class LogoutController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        //获取session对象
        HttpSession session = request.getSession();
        //从session对象中删除teacher,student,sysadmin,eduadmin,loginController属性
        session.removeAttribute("teacher");
        session.removeAttribute("student");
        session.removeAttribute("sysadmin");
        session.removeAttribute("eduadmin");
        //设置会话立即失效
        session.invalidate();
        //返回到登录页面
        response.sendRedirect("loginController");
    }
}
