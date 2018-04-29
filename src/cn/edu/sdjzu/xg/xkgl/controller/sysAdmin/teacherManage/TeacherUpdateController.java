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
            //请求对象参数action的值为"reset"时，先重置密码，再执行更改操作
            if("reset".equals(request.getParameter("action"))){
                //重置密码
                teacherToUpdate.setPassword("123456");
                //执行更改操作
                TeacherService.getInstance().update(teacherToUpdate);
                //重定向到teacherController
                response.sendRedirect("teacherController");
            }else {
                //获取所有职称
                Collection<ProTitle> profTitleSet =
                        ProTitleService.getInstance().findAll();
                //设置profTitleSet属性，teacherToUpdate属性
                request.setAttribute("profTitleSet", profTitleSet);
                request.setAttribute("teacherToUpdate",teacherToUpdate);
                //请求转发到update.jsp
                request.getRequestDispatcher("/pages/sysadmin/teacher/update.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            //异常处理，把错误信息保存在request的message属性中，请求转发到error.jsp
            request.setAttribute("message","更新密码失败");
            request.getRequestDispatcher("/pages/error.jsp").forward(request,response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //获取各个属性值
            //id
            int id = Helper.getIdFromRequest(request);
            //老师对象
            Teacher teacherFromDB = TeacherService.getInstance().find(id);
            //老师名
            String name = request.getParameter("name");
            //编号
            String no =  request.getParameter("no");
            //设置老师对象的各个属性值
            teacherFromDB.setName(name);
            teacherFromDB.setUsername(no);
            teacherFromDB.setNo(no);
            //执行修改操作
            TeacherService.getInstance().update(teacherFromDB);
            //重定向到teacherController
            response.sendRedirect("teacherController");
        } catch (SQLException e) {
            //异常处理，把错误信息保存在request的message属性中，请求转发到error.jsp
            request.setAttribute("message","更新教师信息失败");
            request.getRequestDispatcher("/pages/error.jsp").forward(request,response);
        }
    }
}
