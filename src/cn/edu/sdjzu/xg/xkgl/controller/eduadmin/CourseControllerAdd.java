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
        Collection<CourseType> courseTypeCollection = new HashSet<CourseType>();
        Collection<Teacher> teacherCollection = new HashSet<Teacher>();
        try {
            courseTypeCollection = CourseTypeService.getInstance().findAll();
            teacherCollection =TeacherService.getInstance().findAll();
        } catch (SQLException e) {
            request.setAttribute("message","教师与课程类型查询出错");
            request.getRequestDispatcher("/pages/error.jsp").forward(request,response);
        }
        request.setAttribute("courseTypeSet",courseTypeCollection);
        request.setAttribute("teacherSet",teacherCollection);
        request.getRequestDispatcher("/pages/eduadmin/course/courseAdd.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String no = request.getParameter("courseNo");
        String title = request.getParameter("courseTitle");
        String time = request.getParameter("courseTime");
        int max = Integer.parseInt(request.getParameter("courseMax"));
        int min = Integer.parseInt(request.getParameter("courseMin"));
        int hours = Integer.parseInt(request.getParameter("courseHours"));
        int credit = Integer.parseInt(request.getParameter("courseCredit"));
        int teacherId = Integer.parseInt(request.getParameter("courseTeacher"));
        int courseTypeid = Integer.parseInt(request.getParameter("courseType"));
        Teacher teacher = null;
        CourseType courseType = null;
        try {
            teacher = TeacherService.getInstance().find(teacherId);
            courseType = CourseTypeService.getInstance().findCourseType(courseTypeid);
            Course courseToAdd = new Course(no,title,max,min,0,hours,time,credit,false,teacher,courseType);
            CourseService.getInstance().add(courseToAdd);
        } catch (SQLException e) {
            request.setAttribute("message","添加课程失败");
            request.getRequestDispatcher("/pages/error.jsp").forward(request,response);
        }
        response.sendRedirect("/courseController");
    }

}
