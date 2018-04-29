package cn.edu.sdjzu.xg.xkgl.controller.sysAdmin.studentManage;

import cn.edu.sdjzu.xg.xkgl.domain.Student;
import cn.edu.sdjzu.xg.xkgl.service.StudentService;
import util.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

//将此类声明为Servlet,studentConntroller是该类的映射地址
@WebServlet("/studentController")
public class StudentController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //如果请求参数参数action的值为"delete"，则先执行删除操作
            if("delete".equals(request.getParameter("action"))){
                StudentService.getInstance().delete(Helper.getIdFromRequest(request));
            }
            //获取所有的Student对象
            Collection<Student> students = StudentService.getInstance().findAll();
            //获取students属性值
            request.setAttribute("students",students);
            //请求转发到list.jsp
            request.getRequestDispatcher("pages/sysadmin/student/list.jsp")
                    .forward(request,response);
        } catch (Exception e) {
            //异常处理，把错误信息保存在request的message属性中，请求转发到error.jsp
            request.setAttribute("message",e.getMessage());
            request.getRequestDispatcher("/pages/error.jsp").forward(request,response);
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            StringBuilder conditionSb =  new StringBuilder();
            //获取请求参数string值
            String string = req.getParameter("string");
            conditionSb.append(" name like '%").append(string).append("%' ");
            conditionSb.append("or no like '%").append(string).append("%' ");
            //获取所有的Student对象
            Collection<Student> students = StudentService.getInstance().findAll(conditionSb.toString());
            req.setAttribute("students",students);
            //请求转发到list.jsp
            req.getRequestDispatcher("pages/sysadmin/student/list.jsp")
                    .forward(req,resp);
        }catch (SQLException e) {
            //异常处理，把错误信息保存在request的message属性中，请求转发到error.jsp
            req.setAttribute("message","查询失败");
            req.getRequestDispatcher("/pages/error.jsp").forward(req,resp);
        }
    }
}
