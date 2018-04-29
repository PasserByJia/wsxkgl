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
        try {
            //获得各个属性值
            //老师名
            String name = request.getParameter("name");
            //老师编号
            String no =  request.getParameter("no");
            //性别
            String sex = request.getParameter("sex");
            //根据id获取职称
            ProTitle proTitle =
                    ProTitleService.getInstance().findProTitle(Helper.getIdFromRequest(request,"profTitle"));
            //由获得的属性创建新老师对象
            Teacher teacherToAdd = new Teacher(no,"123456",name,no,sex,proTitle);
            //执行增加操作
            TeacherService.getInstance().add(teacherToAdd);
        } catch (SQLException e) {
            //异常处理，把错误信息保存在request的message属性中，请求转发到error.jsp
            request.setAttribute("message","添加教师失败");
            request.getRequestDispatcher("/pages/error.jsp").forward(request,response);
        }
        //重定向到teacherController
        response.sendRedirect("teacherController");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //获取所有职称对象
            Collection<ProTitle> profTitleSet = ProTitleService.getInstance().findAll();
            //在请求对象中设置profTitleSet属性值
            request.setAttribute("profTitleSet", profTitleSet);
        } catch (SQLException e) {
            //异常处理
            e.printStackTrace();
        }
        //请求转发到add.jsp
        request.getRequestDispatcher("/pages/sysadmin/teacher/add.jsp").forward(request, response);
    }

}
