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

//将此类声明为Servlet,eduAdminConntroller是该类的映射地址
@WebServlet("/eduAdminController")
public class EduAdminController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //如果请求参数action的值为"delete"，则先执行删除操作
            if("delete".equals(req.getParameter("action"))){
                EduAdminService.getInstance().delete(Helper.getIdFromRequest(req));
            }
            //获取所有EduAdmin对象
            Collection<EduAdmin> eduAdmins = EduAdminService.getInstance().findAll();
            //设置eduAdmins属性值
            req.setAttribute("eduAdmins",eduAdmins);
            //请求转发到list.jsp
            req.getRequestDispatcher("/pages/sysadmin/eduadmin/list.jsp")
                    .forward(req,resp);
        } catch (SQLException e) {
            //异常处理
            e.printStackTrace();
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            StringBuilder conditionSb =  new StringBuilder();
            //获取string属性值
            String string = req.getParameter("string");
            conditionSb.append(" name like '%").append(string).append("%' ");
            conditionSb.append("or no like '%").append(string).append("%' ");
            //获取所有EduAdmin对象
            Collection<EduAdmin> eduAdmins = EduAdminService.getInstance().findAll(conditionSb.toString());
            //在请求对象中设置eduAdmins属性值
            req.setAttribute("eduAdmins",eduAdmins);
            //请求转发到list.jsp
            req.getRequestDispatcher("/pages/sysadmin/eduadmin/list.jsp")
                    .forward(req,resp);
        }catch (SQLException e) {
            //异常处理，把错误信息保存在request的message属性中，请求转发到error.jsp
            req.setAttribute("message","查看教务管理员失败");
            req.getRequestDispatcher("/pages/error.jsp").forward(req,resp);
        }
    }
}
