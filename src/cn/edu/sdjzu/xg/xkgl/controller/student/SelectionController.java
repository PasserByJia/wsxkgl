package cn.edu.sdjzu.xg.xkgl.controller.student;

import cn.edu.sdjzu.xg.xkgl.dao.CourseDao;
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

//将此类声明为Servlet,selectionConntroller是该类的映射地址
@WebServlet("/selectionController")
public class SelectionController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*获取可选所有课程*/
        //声明引用
        Collection<Course> courses = null;
        Collection<CourseSelection>  courseSelections = null;
        Collection<CourseType> courseTypes = null;
        //在session对象获得student属性值
        Student student =(Student) req.getSession().getAttribute("student");
        try {
            //获取所有课程，选课结果，课程类型
            courses = CourseDao.getInstance().findAll();
            courseSelections = CourseSelectionService.getCourseSelectionService().findByStudentUsername(student.getUsername());
            courseTypes = CourseTypeDao.getInstance().findAll();
        } catch (SQLException e) {
            //异常处理
            e.printStackTrace();
        }
        //在请求对象中设置courses，courseSelections，courseTypes属性值
        req.setAttribute("courses",courses);
        req.setAttribute("courseSelections",courseSelections);
        req.setAttribute("courseTypes",courseTypes);
        //请求转发到studentSelection.jsp
        req.getRequestDispatcher("/pages/student/studentSelection.jsp").forward(req,resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            //获取所有课程类型
            Collection<CourseType>  courseTypes = CourseTypeDao.getInstance().findAll();
            //设置课程类型属性值存放在request范围内
            req.setAttribute("courseTypes",courseTypes);
            StringBuilder conditionSb =  new StringBuilder();
            //获取课程名参数
            String title = req.getParameter("title");
            //追加SQL语句
            conditionSb.append(" title like '%").append(title).append("%'");
            //获取id
            int courseTypeId = Helper.getIdFromRequest(req,"courseTypeId");
            if(0!=(courseTypeId)){
                //追加SQL语句
                conditionSb.append(" and");
                conditionSb.append(" courseType_id= ").append(courseTypeId);
            }
            //获取student属性值
            Student student =(Student) req.getSession().getAttribute("student");
            Collection<CourseSelection> courseSelections = CourseSelectionService.getCourseSelectionService().findByStudentUsername(student.getUsername());
            //获取所有course对象
            Collection<Course> courses = CourseService.getInstance().findAll(conditionSb.toString());
            //在请求对象中设置courseSelections，courses属性值
            req.setAttribute("courseSelections",courseSelections);
            req.setAttribute("courses",courses);
            //请求转发到studentSelection.jsp
            req.getRequestDispatcher("/pages/student/studentSelection.jsp").forward(req,resp);
        }catch (SQLException e) {
            //异常处理，把错误信息保存在request的message属性中，请求转发到error.jsp
            req.setAttribute("message","结果查询失败");
            req.getRequestDispatcher("/pages/error.jsp").forward(req,resp);
        }
    }
}
