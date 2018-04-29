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

//将此类声明为Servlet,loginConntroller是该类的映射地址
@WebServlet("/loginController")
public class loginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //转发到login.jsp，返回登录页面
        request.getRequestDispatcher("pages/login/login.jsp").forward(request,response);
    }
    /**
     * 由登录页面表单传入数据进行处理
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //获取用户名参数
            String username = request.getParameter("username");
            //获取密码参数
            String password = request.getParameter("password");
            //判断用户类型，获取role指定的参数，role为用户类型
            int role = Integer.parseInt(request.getParameter("role"));
            //获取session对象
            HttpSession httpSession = request.getSession();
            //在session对象中设置role属性
            httpSession.setAttribute("role",role);
            //根据代表不同用户类型的role的值，分别声明参数为username和password的唯一的对应用户类型对象
            //1是任课教师类型，2是学生类型,3是系统管理员类型,4是教务管理员类型
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
    /**
     * 根据用户类型参数分别将不同用户类型指定的对象保存在session对象中，重定向到不同的index.jsp
     * @param user
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    protected void toPages(User user,HttpServletRequest request, HttpServletResponse response) throws IOException ,ServletException{
        HttpSession httpSession = request.getSession();
        //将user存入session用于登录过滤器
        httpSession.setAttribute("user",user);
        //instanceof作用：用来判断其左边对象是否为其右边类的实例，返回boolean类型的数据
        if(user instanceof Teacher){
            httpSession.setAttribute("teacher",(Teacher)user);
            response.sendRedirect("pages/teacher/menu/index.jsp");
        }else if(user instanceof Student){
            httpSession.setAttribute("student",(Student)user);
            response.sendRedirect("pages/student/menu/index.jsp");
        }else if(user instanceof EduAdmin){
            httpSession.setAttribute("eduadmin",(EduAdmin)user);
            response.sendRedirect("pages/eduadmin/index.jsp");
        }else if(user instanceof SysAdmin){
            httpSession.setAttribute("sysadmin",(SysAdmin)user);
            response.sendRedirect("pages/sysadmin/index.jsp");
        }else {
            //没有获得用户类型则不能登录，返回登录界面
            request.getRequestDispatcher("pages/login/login.jsp").forward(request,response);
        }
    }
}

