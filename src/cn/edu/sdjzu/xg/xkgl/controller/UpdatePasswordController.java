package cn.edu.sdjzu.xg.xkgl.controller;

import cn.edu.sdjzu.xg.xkgl.domain.*;
import cn.edu.sdjzu.xg.xkgl.service.EduAdminService;
import cn.edu.sdjzu.xg.xkgl.service.StudentService;
import cn.edu.sdjzu.xg.xkgl.service.SysAdminService;
import cn.edu.sdjzu.xg.xkgl.service.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthTableUI;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/updatepassword")
public class UpdatePasswordController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        String password1 = req.getParameter("password1");
        String password2 = req.getParameter("password2");
        //如果逻辑结果为true，进入此if语句
        if (password1.equals(password2)){
            //获取会话对象
            HttpSession httpSession = req.getSession();
            //通过属性名"role"从会话域中获取属性值,并存储到role 变量中
            int role = (int)httpSession.getAttribute("role");
            try {
                switch (role){
                    case 1:
                        //通过属性名"teacher"从会话域中获取相应属性值
                        Teacher  teacher = (Teacher) httpSession.getAttribute("teacher");
                        //设置密码
                        teacher.setPassword(password1);
                        //更新老师的相应信息
                        TeacherService.getInstance().update(teacher);
                        //结束switch语句
                        break;
                    case 2:
                        //通过属性名"student"从会话域中获取相应属性值
                        Student student = (Student) httpSession.getAttribute("student");
                        //设置学生的密码
                        student.setPassword(password1);
                        //更新学生的相应信息
                        StudentService.getInstance().update(student);
                        //结束switch语句
                        break;
                    case 3:
                        //通过属性名"sysadmin"从会话域中获取相应属性值
                        SysAdmin sysAdmin = (SysAdmin)httpSession.getAttribute("sysadmin");
                        // 设置密码
                        sysAdmin.setPassword(password1);
                        //更新管理员相应信息
                        SysAdminService.getInstance().update(sysAdmin);
                        break;
                    case 4:
                        //通过属性名"eduadmin"从会话域中获取相应属性值
                        EduAdmin eduAdmin = (EduAdmin)httpSession.getAttribute("eduadmin");
                        //设置密码
                        eduAdmin.setPassword(password1);
                        //更新管理员相应的信息
                        EduAdminService.getInstance().update(eduAdmin);
                        break;
                }
            } catch (SQLException e) {
                //将message属性值获取到的异常信息存储到request对象中
                req.setAttribute("message","修改密码失败");
                //请求重定向到指定页面
                req.getRequestDispatcher("/pages/error.jsp").forward(req,resp);
            }
            //返回登录页面，提醒用户信息
            req.setAttribute("msg","<font color=red>密码修改成功</font>");
            //转发到login.jap
            req.getRequestDispatcher("/pages/updatePassword.jsp").forward(req,resp);
        }else {
            //返回登录页面，提醒用户信息
            req.setAttribute("msg","<font color=red>两次密码不一致</font>");
            //转发到login.jap
            req.getRequestDispatcher("/pages/updatePassword.jsp").forward(req,resp);
        }

    }
}
