/***
 *1.增加loginservice
 *2.将重复可用代码抽出
 * 3.在抽象出一个user
 */


package cn.edu.sdjzu.xg.xkgl.controller;

import cn.edu.sdjzu.xg.xkgl.domain.*;
import cn.edu.sdjzu.xg.xkgl.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

@WebServlet("/loginController")
public class loginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //转发到login.jsp
        request.getRequestDispatcher("pages/login/login.jsp").forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //获取用户名参数
            String username = request.getParameter("username");
            //获取密码参数
            String password = request.getParameter("password");
            //根据role判断用户类型
            int role = Integer.parseInt(request.getParameter("role"));
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("role",role);
            switch (role){
                case 1:
                    Teacher teacher = LoginService.getInstance().findTeacher(username,password);
                    this.toPages(teacher,request,response);
                    break;
                case 2:
                    Student student = LoginService.getInstance().findStudent(username,password);
                    this.toPages(student,request,response);
                    break;
                case 3:
                    SysAdmin sysAdmin = LoginService.getInstance().findSysAdmin(username,password);
                    this.toPages(sysAdmin,request,response);
                    break;
                case 4:
                    EduAdmin eduAdmin= LoginService.getInstance().findEduAdmin(username,password);
                    this.toPages(eduAdmin,request,response);
                    break;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    protected void toPages(User user,HttpServletRequest request, HttpServletResponse response) throws IOException ,ServletException{
        if(user instanceof Teacher){
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("teacher",(Teacher)user);
            response.sendRedirect("pages/teacher/menu/index.jsp");
        }else if(user instanceof Student){
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("student",(Student)user);
            response.sendRedirect("pages/student/menu/index.jsp");
        }else if(user instanceof EduAdmin){
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("eduadmin",(EduAdmin)user);
            response.sendRedirect("pages/eduadmin/index.jsp");
        }else if(user instanceof SysAdmin){
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("sysadmin",(SysAdmin)user);
            response.sendRedirect("pages/sysadmin/index.jsp");
        }else {
            request.getRequestDispatcher("pages/login/login.jsp").forward(request,response);
        }
    }
}

