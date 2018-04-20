package cn.edu.sdjzu.xg.xkgl.controller.sysAdmin.teacherManage;

import cn.edu.sdjzu.xg.xkgl.domain.ProTitle;
import cn.edu.sdjzu.xg.xkgl.domain.Teacher;
import cn.edu.sdjzu.xg.xkgl.service.ProTitleService;
import cn.edu.sdjzu.xg.xkgl.service.TeacherService;
import util.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

@WebServlet("/teacherUpdateController")
public class TeacherUpdateController  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Teacher teacherToUpdate = TeacherService.getInstance().find(Helper.getIdFromRequest(request));
            if("reset".equals(request.getParameter("action"))){
                teacherToUpdate.setPassword("123456");
                TeacherService.getInstance().update(teacherToUpdate);
                response.sendRedirect("teacherController");
            }else {
                Collection<ProTitle> profTitleSet =
                        ProTitleService.getInstance().findAll();
                request.setAttribute("profTitleSet", profTitleSet);
                request.setAttribute("teacherToUpdate",teacherToUpdate);
                request.getRequestDispatcher("/pages/sysadmin/teacher/update.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            request.setAttribute("message","更新密码失败");
            request.getRequestDispatcher("/pages/error.jsp").forward(request,response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Helper.getIdFromRequest(request);
            Teacher teacherFromDB = TeacherService.getInstance().find(id);
            String name = request.getParameter("name");
            String no =  request.getParameter("no");
            teacherFromDB.setName(name);
            teacherFromDB.setUsername(no);
            teacherFromDB.setNo(no);
            TeacherService.getInstance().update(teacherFromDB);
            response.sendRedirect("teacherController");
        } catch (SQLException e) {
            request.setAttribute("message","更新教师信息失败");
            request.getRequestDispatcher("/pages/error.jsp").forward(request,response);
        }
    }
}
