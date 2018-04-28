package cn.edu.sdjzu.xg.xkgl.controller.eduadmin;

import cn.edu.sdjzu.xg.xkgl.domain.Course;
import cn.edu.sdjzu.xg.xkgl.domain.CourseSelection;
import cn.edu.sdjzu.xg.xkgl.domain.CourseType;
import cn.edu.sdjzu.xg.xkgl.domain.Teacher;
import cn.edu.sdjzu.xg.xkgl.service.CourseService;
import cn.edu.sdjzu.xg.xkgl.service.CourseTypeService;
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

@WebServlet("/courseControllerUpdate")
public class CourseControllerUpdate extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求参数
        int id = Helper.getIdFromRequest(request);
        //创建集合对象
        Collection<CourseType> courseTypeCollection = null;
        Collection<Teacher> teacherCollection = null;
        //创建Course对象
        Course courseToUpdate = null;
        try {
            //获取CourseTypeService实例，调用相应方法获取所有的CourseType对象,并存储到相应的集合对象中
            courseTypeCollection = CourseTypeService.getInstance().findAll();
            teacherCollection =TeacherService.getInstance().findAll();
            //根据id获取相应的课程对象
            courseToUpdate = CourseService.getInstance().find(id);
            //把courseToUpdate对象存储到请求属性中
            request.setAttribute("courseToUpdate",courseToUpdate);
            request.setAttribute("courseTypeSet",courseTypeCollection);
            request.setAttribute("teacherSet",teacherCollection);
            //转发到courseUpdate\.jsp
            request.getRequestDispatcher("/pages/eduadmin/course/courseUpdate.jsp").forward(request,response);
        } catch (SQLException e) {
            //打印异常信息
            e.printStackTrace();
        }


    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,NullPointerException {
        //设置请求编码类型
        request.setCharacterEncoding("UTF-8");
        //获得各个参数
        int id = Integer.parseInt(request.getParameter("id"));
        String no = request.getParameter("courseNo");
        String title = request.getParameter("courseTitle");
        int max = Integer.parseInt(request.getParameter("courseMax"));
        int min = Integer.parseInt(request.getParameter("courseMin"));
        int hours = Integer.parseInt(request.getParameter("courseHours"));
        int credit = Integer.parseInt(request.getParameter("courseCredit"));
        String time = request.getParameter("courseTime");
        int teacherId = Integer.parseInt(request.getParameter("courseTeacher"));
        Teacher teacher = null;
        int courseTypeId = Integer.parseInt(request.getParameter("courseType"));
        CourseType courseType = null;
        try {
            //根据id获取相应课程的已选课人数
            int accumulation = CourseService.getInstance().find(id).getAccumulation();
            //获取课程状态
            boolean status = CourseService.getInstance().find(id).isStatus();
            //获取指定id的老师，赋值给teacher变量
            teacher = TeacherService.getInstance().find(teacherId);
            courseType = CourseTypeService.getInstance().findCourseType(courseTypeId);
            //创建课程对象
            Course courseUpdated = new Course(id,no,title,max,min,accumulation,hours,time,credit,status,teacher,courseType);
            //更新课程相关信息
            CourseService.getInstance().update(courseUpdated);
            //重定向到指定路径
            response.sendRedirect("/courseController");
        } catch (SQLException e) {
            //打印错误信息
            e.printStackTrace();
        }

    }
}
