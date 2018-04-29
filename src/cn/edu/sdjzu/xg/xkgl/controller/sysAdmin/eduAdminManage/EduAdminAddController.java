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

//将此类声明为Servlet,eduAdmninAddConntroller是该类的映射地址
@WebServlet("/eduAdminAddController")
public class EduAdminAddController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //获取各个属性值
            String name = request.getParameter("name");
            String no =  request.getParameter("no");
            String sex = request.getParameter("sex");
            //创建新EduAdmin对象，执行增加操作
            EduAdmin eduAdminToAdd = new EduAdmin("123456",no,name,no,sex);
            EduAdminService.getInstance().add(eduAdminToAdd);
            //重定向到eduAdminController
            response.sendRedirect("eduAdminController");
        } catch (SQLException e) {
            //异常处理，把错误信息保存在request的message属性中，请求转发到error.jsp
            request.setAttribute("message","添加教务管理员失败");
            request.getRequestDispatcher("/pages/error.jsp").forward(request,response);
        }
    }
}
