package cn.edu.sdjzu.xg.xkgl.controller.sysAdmin.studentManage;

import cn.edu.sdjzu.xg.xkgl.domain.Student;

import cn.edu.sdjzu.xg.xkgl.service.StudentService;
import util.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/studentUpdateController")
public class StudentUpdateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Student studentToUpdate = StudentService.getInstance().find(Helper.getIdFromRequest(request));
            if("reset".equals(request.getParameter("action"))){
                studentToUpdate.setPassword("123456");
                StudentService.getInstance().update(studentToUpdate);
                response.sendRedirect("studentController");
            }else {
                request.setAttribute("studentToUpdate",studentToUpdate);
                request.getRequestDispatcher("/pages/sysadmin/student/update.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            request.setAttribute("message","重置密码失败");
            request.getRequestDispatcher("/pages/error.jsp").forward(request,response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Helper.getIdFromRequest(request);
            Student studentFromDB = StudentService.getInstance().find(id);
            String name = request.getParameter("name");
            String no =  request.getParameter("no");
            String sex = request.getParameter("sex");
            studentFromDB.setName(name);
            studentFromDB.setUsername(no);
            studentFromDB.setNo(no);
            studentFromDB.setSex(sex);
            StudentService.getInstance().update(studentFromDB);
            response.sendRedirect("studentController");
        } catch (SQLException e) {
            request.setAttribute("message","更新学生信息出错");
            request.getRequestDispatcher("/pages/error.jsp").forward(request,response);
        }
    }
}
