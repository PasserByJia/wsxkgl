package cn.edu.sdjzu.xg.xkgl.controller.eduadmin;


import cn.edu.sdjzu.xg.xkgl.dao.CourseTypeDao;
import cn.edu.sdjzu.xg.xkgl.domain.Course;
import cn.edu.sdjzu.xg.xkgl.domain.CourseSelection;
import cn.edu.sdjzu.xg.xkgl.domain.CourseType;
import cn.edu.sdjzu.xg.xkgl.domain.Student;
import cn.edu.sdjzu.xg.xkgl.service.CourseSelectionService;
import cn.edu.sdjzu.xg.xkgl.service.CourseService;
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
import java.util.TreeSet;

@WebServlet("/courseController")
public class CourseController extends HttpServlet {
    //doGet方法
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        //创建集合类对象courses，courseTypes
        Collection<Course> courses = null;
        Collection<CourseType> courseTypes = null;
        try{
            //获取CourseService实例，调用相应方法获取所有的course对象
            courses = CourseService.getInstance().findAll();
            courseTypes = CourseTypeDao.getInstance().findAll();
            //将courseType属性的属性值courseTypes存储到request对象中
            req.setAttribute("courseTypes",courseTypes);
            req.setAttribute("courses",courses);
            //请求转发到指定页面
            req.getRequestDispatcher("pages/eduadmin/course/courseList.jsp").forward(req,resp);

        }catch(SQLException e){
            //打印异常信息
            e.printStackTrace();
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            //获取CourseService实例，调用相应方法获取所有的course对象,并存储到相应的集合对象中
            Collection<CourseType>  courseTypes = CourseTypeDao.getInstance().findAll();
            //将courseType属性的属性值courseTypes存储到request对象中
            req.setAttribute("courseTypes",courseTypes);
            //创建StringBuilder对象
            StringBuilder conditionSb =  new StringBuilder();
            //获取请求参数的值
            String title = req.getParameter("title");
            //设置目标sql语句
            conditionSb.append(" title like '%").append(title).append("%'");
            //获取请求参数courseTypeId的值
            int courseTypeId = Helper.getIdFromRequest(req,"courseTypeId");
            if(0!=(courseTypeId)){
                //继续追加目标sql语句的内容
                conditionSb.append(" and");
                conditionSb.append(" courseType_id= ").append(courseTypeId);
            }
            //获取符合目标sql语句的课程，并存储到相应的集合对象中
            Collection<Course> courses = CourseService.getInstance().findAll(conditionSb.toString());
            req.setAttribute("courses",courses);
            //请求转发到指定页面
            req.getRequestDispatcher("pages/eduadmin/course/courseList.jsp").forward(req,resp);
        }catch (SQLException e) {
            //将message属性的属性值message存储到request对象中
            req.setAttribute("message","结果查询失败");
            //请求转发到指定error页面
            req.getRequestDispatcher("/pages/error.jsp").forward(req,resp);
        }
    }
}
