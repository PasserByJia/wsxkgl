package cn.edu.sdjzu.xg.xkgl.controller.sysAdmin.eduAdminManage;

import cn.edu.sdjzu.xg.xkgl.domain.EduAdmin;
import cn.edu.sdjzu.xg.xkgl.service.EduAdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/eduAdminAddController")
public class EduAdminAddController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            String no =  request.getParameter("no");
            String sex = request.getParameter("sex");
            EduAdmin eduAdminToAdd = new EduAdmin("123456",no,name,no,sex);
            EduAdminService.getInstance().add(eduAdminToAdd);
            response.sendRedirect("eduAdminController");
        } catch (SQLException e) {
            request.setAttribute("message","添加教务管理员失败");
            request.getRequestDispatcher("/pages/error.jsp").forward(request,response);
        }
    }
}
