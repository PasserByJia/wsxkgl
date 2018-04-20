package cn.edu.sdjzu.xg.xkgl.controller.eduadmin;

import cn.edu.sdjzu.xg.xkgl.domain.Course;
import cn.edu.sdjzu.xg.xkgl.domain.CourseType;
import cn.edu.sdjzu.xg.xkgl.service.CourseService;
import cn.edu.sdjzu.xg.xkgl.service.CourseTypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;

@WebServlet("/lookupCourseController")
public class LookupCourseController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lookup = req.getParameter("lookupCourse");
        String name = req.getParameter("name");

        int a = Integer.parseInt(lookup);
        switch (a){
            case 1:
                resp.sendRedirect("/courseController");
                break;
            case 2:
                Collection<Course> course = new HashSet<Course>();
                try {
                    course = CourseService.getInstance().findByTitle(name);
                    req.setAttribute("course",course);
                    req.getRequestDispatcher("pages/eduadmin/course/courseList.jsp").forward(req,resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                Collection<Course> courseCollection = new HashSet<Course>();
                try {
                    Collection<CourseType> courseType = CourseTypeService.getInstance().findByDes(name);
                    courseCollection = CourseService.getInstance().findByType(courseType);
                    req.setAttribute("courses",courseCollection);
                    req.getRequestDispatcher("pages/eduadmin/course/courseList.jsp").forward(req,resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
