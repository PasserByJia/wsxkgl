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
            //如果请求参数参数action的值为"delete"，则先执行删除操作
            if("delete".equals(request.getParameter("action"))){
                TeacherService.getInstance().delete(Helper.getIdFromRequest(request));
            }
            //获得所有的Teacher对象
            Collection<Teacher> teachers = TeacherService.getInstance().findAll();
            //存入请求属性中
            request.setAttribute("teachers",teachers);
            //转发到教师列表页面
            request.getRequestDispatcher("pages/sysadmin/teacher/list.jsp")
                    .forward(request,response);
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
            System.out.println(conditionSb.toString());
            Collection<Teacher> teachers = TeacherService.getInstance().findAll(conditionSb.toString());
            req.setAttribute("teachers",teachers);
            req.getRequestDispatcher("pages/sysadmin/teacher/list.jsp")
                    .forward(req,resp);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
