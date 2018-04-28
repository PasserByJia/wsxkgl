package cn.edu.sdjzu.xg.xkgl.controller.eduadmin;

import cn.edu.sdjzu.xg.xkgl.domain.Course;
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
import java.util.HashSet;

@WebServlet("/courseControllerAdd")
public class CourseControllerAdd extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //创建集合对象courseTypeCollection，teacherCollection
        Collection<CourseType> courseTypeCollection = new HashSet<CourseType>();
        Collection<Teacher> teacherCollection = new HashSet<Teacher>();
        try {
            //获取CourseTypeService实例，调用相应方法获取所有的courseType对象,并存储到相应的集合对象中
            courseTypeCollection = CourseTypeService.getInstance().findAll();
            teacherCollection =TeacherService.getInstance().findAll();
            //设置相应属性的相应属性值并存储到request对象中
            request.setAttribute("courseTypeSet",courseTypeCollection);
            request.setAttribute("teacherSet",teacherCollection);
            request.getRequestDispatcher("/pages/eduadmin/course/courseAdd.jsp").forward(request,response);
        } catch (SQLException e) {
            //将message属性的属性值存储到request对象中
            request.setAttribute("message","教师与课程类型查询出错");
            //请求转发到指定页面
            request.getRequestDispatcher("/pages/error.jsp").forward(request,response);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求参数，并存储到相应变量中
        //编号
        String no = request.getParameter("courseNo");
        //名称
        String title = request.getParameter("courseTitle");
        //选课时间
        String time = request.getParameter("courseTime");
        //最大选课人数
        int max = Integer.parseInt(request.getParameter("courseMax"));
        //最小选课人数
        int min = Integer.parseInt(request.getParameter("courseMin"));
        //课时
        int hours = Integer.parseInt(request.getParameter("courseHours"));
        //学分
        int credit = Integer.parseInt(request.getParameter("courseCredit"));
        //教师id
        int teacherId = Integer.parseInt(request.getParameter("courseTeacher"));
        int courseTypeid = Integer.parseInt(request.getParameter("courseType"));
        Teacher teacher = null;
        CourseType courseType = null;
        try {
            //找到与id对象的Teacher对象
            teacher = TeacherService.getInstance().find(teacherId);
            courseType = CourseTypeService.getInstance().findCourseType(courseTypeid);
            //创建课程对象
            Course courseToAdd = new Course(no,title,max,min,0,hours,time,credit,false,teacher,courseType);
            //增加课程
            CourseService.getInstance().add(courseToAdd);
            //重定向到指定页面
            response.sendRedirect("/courseController");
        } catch (SQLException e) {
            //设置message属性的相应属性值并存储到request对象中
            request.setAttribute("message","添加课程失败");
            request.getRequestDispatcher("/pages/error.jsp").forward(request,response);
        }

    }

}
