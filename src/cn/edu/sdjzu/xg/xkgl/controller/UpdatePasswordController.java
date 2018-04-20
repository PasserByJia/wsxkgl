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
        String password1 = req.getParameter("password1");
        String password2 = req.getParameter("password2");
        if (password1.equals(password2)){

            HttpSession httpSession = req.getSession();
            int role = (int)httpSession.getAttribute("role");
            try {
                switch (role){
                    case 1:
                        Teacher  teacher = (Teacher) httpSession.getAttribute("teacher");
                        teacher.setPassword(password1);
                        TeacherService.getInstance().update(teacher);
                        break;
                    case 2:
                        Student student = (Student) httpSession.getAttribute("student");
                        student.setPassword(password1);
                        StudentService.getInstance().update(student);
                        break;
                    case 3:
                        SysAdmin sysAdmin = (SysAdmin)httpSession.getAttribute("sysadmin");
                        sysAdmin.setPassword(password1);
                        SysAdminService.getInstance().update(sysAdmin);
                        break;
                    case 4:
                        EduAdmin eduAdmin = (EduAdmin)httpSession.getAttribute("eduadmin");
                        eduAdmin.setPassword(password1);
                        EduAdminService.getInstance().update(eduAdmin);
                        break;
                }
            } catch (SQLException e) {
                req.setAttribute("message","修改密码失败");
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
