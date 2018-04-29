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

//将此类声明为Servlet,studentUpdateConntroller是该类的映射地址
@WebServlet("/studentUpdateController")
public class StudentUpdateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //获取要更新的学生
            Student studentToUpdate = StudentService.getInstance().find(Helper.getIdFromRequest(request));
            //根据请求，决定是否重置密码
            if("reset".equals(request.getParameter("action"))){
                //重置密码
                studentToUpdate.setPassword("123456");
                //更新学生信息
                StudentService.getInstance().update(studentToUpdate);
                //重定向到studentController
                response.sendRedirect("studentController");
            }else {
                //将属性studentToUpdate的属性值存储到request对象中
                request.setAttribute("studentToUpdate",studentToUpdate);
                //重定向到指定页面
                request.getRequestDispatcher("/pages/sysadmin/student/update.jsp").forward(request,response);
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
            //获取请求参数，并存储到相应变量中
            int id = Helper.getIdFromRequest(request);
            //根据id,找到相应的学生
            Student studentFromDB = StudentService.getInstance().find(id);
            //获取请求参数，并存储到相应变量中
            //姓名
            String name = request.getParameter("name");
            //编号
            String no =  request.getParameter("no");
            //性别
            String sex = request.getParameter("sex");
            //修改学生信息
            studentFromDB.setName(name);
            studentFromDB.setUsername(no);
            studentFromDB.setNo(no);
            studentFromDB.setSex(sex);
            //更新学生信息
            StudentService.getInstance().update(studentFromDB);
            //重定向到指定页面
            response.sendRedirect("studentController");
        } catch (SQLException e) {
            //异常处理，把错误信息保存在request的message属性中，请求转发到error.jsp
            request.setAttribute("message","更新学生信息出错");
            request.getRequestDispatcher("/pages/error.jsp").forward(request,response);
        }
    }
}
