package cn.edu.sdjzu.xg.xkgl.controller.sysAdmin.eduAdminManage;

import cn.edu.sdjzu.xg.xkgl.domain.EduAdmin;
import cn.edu.sdjzu.xg.xkgl.service.EduAdminService;
import util.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/eduAdminUpdateController")
public class EduAdminUpdateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            EduAdmin eduAdminToUpdate = EduAdminService.getInstance().find(Helper.getIdFromRequest(request));
            if("reset".equals(request.getParameter("action"))){
                eduAdminToUpdate.setPassword("123456");
                EduAdminService.getInstance().update(eduAdminToUpdate);
                response.sendRedirect("eduAdminController");
            }else {
                request.setAttribute("eduAdminToUpdate",eduAdminToUpdate);
                request.getRequestDispatcher("/pages/sysadmin/eduadmin/update.jsp").forward(request,response);
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
            EduAdmin eduAdminFromDB = EduAdminService.getInstance().find(id);
            String name = request.getParameter("name");
            String no =  request.getParameter("no");
            String sex = request.getParameter("sex");
            eduAdminFromDB.setName(name);
            eduAdminFromDB.setNo(no);
            eduAdminFromDB.setUsername(no);
            eduAdminFromDB.setSex(sex);
            EduAdminService.getInstance().update(eduAdminFromDB);
            response.sendRedirect("eduAdminController");
        } catch (SQLException e) {
            request.setAttribute("message","更新教务管理员信息失败");
            request.getRequestDispatcher("/pages/error.jsp").forward(request,response);
        }
    }
}
