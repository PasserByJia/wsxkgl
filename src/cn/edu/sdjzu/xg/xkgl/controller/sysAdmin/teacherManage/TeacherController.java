package cn.edu.sdjzu.xg.xkgl.controller.sysAdmin.teacherManage;

import cn.edu.sdjzu.xg.xkgl.domain.Teacher;
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

@WebServlet("/teacherController")
public class TeacherController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //请求参数action值为“delete”，则执行删除操作
            if("delete".equals(request.getParameter("action"))){
                TeacherService.getInstance().delete(Helper.getIdFromRequest(request));
            }
            //获取所有老师对象
            Collection<Teacher> teachers = TeacherService.getInstance().findAll();
            //请求对象中设置老师属性值
            request.setAttribute("teachers",teachers);
            //请求转发到list.jsp
            request.getRequestDispatcher("pages/sysadmin/teacher/list.jsp")
                    .forward(request,response);
        } catch (Exception e) {
            //异常处理，把错误信息保存在request的message属性中
            request.setAttribute("message",e.getMessage());
            //请求转发到error.jsp
            request.getRequestDispatcher("/pages/error.jsp").forward(request,response);
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            //创建 StringBuilder 对象
            StringBuilder conditionSb =  new StringBuilder();
            String string = req.getParameter("string");
            //追加SQL语句
            conditionSb.append(" name like '%").append(string).append("%' ");
            conditionSb.append("or no like '%").append(string).append("%' ");
            System.out.println(conditionSb.toString());
            //获取所有老师对象
            Collection<Teacher> teachers = TeacherService.getInstance().findAll(conditionSb.toString());
            req.setAttribute("teachers",teachers);
            //请求转发到list.jsp
            req.getRequestDispatcher("pages/sysadmin/teacher/list.jsp")
                    .forward(req,resp);
        }catch (SQLException e) {
            //异常处理，把错误信息保存在request的message属性中
            req.setAttribute("message","查询失败");
            //请求转发到error.jsp
            req.getRequestDispatcher("/pages/error.jsp").forward(req,resp);
        }
    }
}
