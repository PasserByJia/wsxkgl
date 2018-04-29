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

//将此类声明为Servlet,eduAminUpdateConntroller是该类的映射地址
@WebServlet("/eduAdminUpdateController")
public class EduAdminUpdateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //获取所有的EduAdmin对象
            EduAdmin eduAdminToUpdate = EduAdminService.getInstance().find(Helper.getIdFromRequest(request));
            //如果请求参数action值为"reset"，则重置密码，执行修改操作
            if("reset".equals(request.getParameter("action"))){
                //重置密码
                eduAdminToUpdate.setPassword("123456");
                //执行修改操作
                EduAdminService.getInstance().update(eduAdminToUpdate);
                //重定向到eduAdminController
                response.sendRedirect("eduAdminController");
            }else {
                //获取eduAdminToUpdate属性值
                request.setAttribute("eduAdminToUpdate",eduAdminToUpdate);
                //请求转发到update.jsp
                request.getRequestDispatcher("/pages/sysadmin/eduadmin/update.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            //异常处理，把错误信息保存在request的message属性中，请求转发到error.jsp
            request.setAttribute("message","重置密码失败");
            request.getRequestDispatcher("/pages/error.jsp").forward(request,response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //获取id
            int id = Helper.getIdFromRequest(request);
            //获取id对应的EduAdmin对象
            EduAdmin eduAdminFromDB = EduAdminService.getInstance().find(id);
            //获取请求参数值
            String name = request.getParameter("name");
            String no =  request.getParameter("no");
            String sex = request.getParameter("sex");
            //获取要修改的EduAdmin对象各个属性值，保存在EduAdmin对象
            eduAdminFromDB.setName(name);
            eduAdminFromDB.setNo(no);
            eduAdminFromDB.setUsername(no);
            eduAdminFromDB.setSex(sex);
            //执行修改操作
            EduAdminService.getInstance().update(eduAdminFromDB);
            //重定向到eduAdminController
            response.sendRedirect("eduAdminController");
        } catch (SQLException e) {
            //异常处理，把错误信息保存在request的message属性中，请求转发到error.jsp
            request.setAttribute("message","更新教务管理员信息失败");
            request.getRequestDispatcher("/pages/error.jsp").forward(request,response);
        }
    }
}
