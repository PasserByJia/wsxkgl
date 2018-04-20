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
        int id = Helper.getIdFromRequest(request);
        Collection<CourseType> courseTypeCollection = null;
        Collection<Teacher> teacherCollection = null;
        //根据id获取对应的Course对象
        Course courseToUpdate = null;
        try {
            courseTypeCollection = CourseTypeService.getInstance().findAll();
            teacherCollection =TeacherService.getInstance().findAll();
            courseToUpdate = CourseService.getInstance().find(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //把courseToUpdate对象存放在请求属性中request.setAttribute()
        request.setAttribute("courseToUpdate",courseToUpdate);
        request.setAttribute("courseTypeSet",courseTypeCollection);
        request.setAttribute("teacherSet",teacherCollection);
        //转发到courseUpdate\.jsp
        request.getRequestDispatcher("/pages/eduadmin/course/courseUpdate.jsp").forward(request,response);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,NullPointerException {
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
            int accumulation = CourseService.getInstance().find(id).getAccumulation();
            boolean status = CourseService.getInstance().find(id).isStatus();
            teacher = TeacherService.getInstance().find(teacherId);
            courseType = CourseTypeService.getInstance().findCourseType(courseTypeId);
            Course courseUpdated = new Course(id,no,title,max,min,accumulation,hours,time,credit,status,teacher,courseType);
            CourseService.getInstance().update(courseUpdated);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/courseController");
    }
}
