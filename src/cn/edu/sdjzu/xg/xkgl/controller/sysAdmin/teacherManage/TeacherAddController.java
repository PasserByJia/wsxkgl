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

@WebServlet("/teacherAddController")
public class TeacherAddController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try { ;
            String name = request.getParameter("name");
            String no =  request.getParameter("no");
            String sex = request.getParameter("sex");
            ProTitle proTitle =
                    ProTitleService.getInstance().findProTitle(Helper.getIdFromRequest(request,"profTitle"));
            Teacher teacherToAdd = new Teacher(no,"123456",name,no,sex,proTitle);
            TeacherService.getInstance().add(teacherToAdd);
        } catch (SQLException e) {
            request.setAttribute("message","添加教师失败");
            request.getRequestDispatcher("/pages/error.jsp").forward(request,response);
        }
        response.sendRedirect("teacherController");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
           Collection<ProTitle> profTitleSet = ProTitleService.getInstance().findAll();
          request.setAttribute("profTitleSet", profTitleSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/pages/sysadmin/teacher/add.jsp").forward(request, response);
    }

}
