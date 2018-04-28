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
        //获取请求参数
        String lookup = req.getParameter("lookupCourse");
        String name = req.getParameter("name");
        System.out.println(name);
        //不同lookup的值对应不同的页面
        int a = Integer.parseInt(lookup);
        switch (a){
            case 1:
                //如果a的值为1，请求重定向到指定页面
                resp.sendRedirect("/courseController");
                //结束switch语句
                break;
            case 2:
                //创建集合对象
                Collection<Course> course = new HashSet<Course>();
                try {
                    //通过name参数的值找到指定的课程
                    course = CourseService.getInstance().findByTitle(name);
                    //将course属性的属性值course存储到request对象中
                    req.setAttribute("course",course);
                    //请求转发到指定页面
                    req.getRequestDispatcher("pages/eduadmin/course/courseList.jsp").forward(req,resp);
                } catch (SQLException e) {
                    //打印异常信息
                    e.printStackTrace();
                }
                //结束switch语句
                break;
            case 3:
                //创建集合对象
                Collection<Course> courseCollection = new HashSet<Course>();
                try {
                    //通过传入的name值找到符合条件的相应课程类型，并存储到集合对象中去
                    Collection<CourseType> courseType = CourseTypeService.getInstance().findByDes(name);
                    //通过传入的courseType 找到相同courseType的课程，并存储到集合对象中
                    courseCollection = CourseService.getInstance().findByType(courseType);
                    //将courses属性的属性值courseCollection存储到request对象中
                    req.setAttribute("courses",courseCollection);
                    //请求转发到指定页面
                    req.getRequestDispatcher("pages/eduadmin/course/courseList.jsp").forward(req,resp);
                } catch (SQLException e) {
                    //打印异常信息
                    e.printStackTrace();
                }
                //结束switch语句
                break;
        }
    }
}
