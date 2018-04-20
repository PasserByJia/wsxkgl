package cn.edu.sdjzu.xg.xkgl.controller.sysAdmin.eduAdminManage;

import cn.edu.sdjzu.xg.xkgl.dao.CourseTypeDao;
import cn.edu.sdjzu.xg.xkgl.domain.Course;
import cn.edu.sdjzu.xg.xkgl.domain.CourseType;
import cn.edu.sdjzu.xg.xkgl.domain.EduAdmin;
import cn.edu.sdjzu.xg.xkgl.service.CourseService;
import cn.edu.sdjzu.xg.xkgl.service.EduAdminService;
import util.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

@WebServlet("/eduAdminController")
public class EduAdminController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //如果请求参数参数action的值为"delete"，则先执行删除操作
            if("delete".equals(req.getParameter("action"))){
                EduAdminService.getInstance().delete(Helper.getIdFromRequest(req));
            }
            Collection<EduAdmin> eduAdmins = EduAdminService.getInstance().findAll();
            req.setAttribute("eduAdmins",eduAdmins);
            req.getRequestDispatcher("/pages/sysadmin/eduadmin/list.jsp")
                    .forward(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            StringBuilder conditionSb =  new StringBuilder();
            String string = req.getParameter("string");
            conditionSb.append(" name like '%").append(string).append("%' ");
            conditionSb.append("or no like '%").append(string).append("%' ");
            Collection<EduAdmin> eduAdmins = EduAdminService.getInstance().findAll(conditionSb.toString());
            req.setAttribute("eduAdmins",eduAdmins);
            req.getRequestDispatcher("/pages/sysadmin/eduadmin/list.jsp")
                    .forward(req,resp);
        }catch (SQLException e) {
            req.setAttribute("message","查看教务管理员失败");
            req.getRequestDispatcher("/pages/error.jsp").forward(req,resp);
        }
    }
}
