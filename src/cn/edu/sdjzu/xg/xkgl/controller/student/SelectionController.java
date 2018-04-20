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

@WebServlet("/selectionController")
public class SelectionController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*获取可选所有课程*/
        Collection<Course> courses = null;
        Collection<CourseSelection>  courseSelections = null;
        Collection<CourseType> courseTypes = null;
        Student student =(Student) req.getSession().getAttribute("student");
        try {
            courses = CourseDao.getInstance().findAll();
            courseSelections = CourseSelectionService.getCourseSelectionService().findByStudentUsername(student.getUsername());
            courseTypes = CourseTypeDao.getInstance().findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("courses",courses);
        req.setAttribute("courseSelections",courseSelections);
        req.setAttribute("courseTypes",courseTypes);
        req.getRequestDispatcher("/pages/student/studentSelection.jsp").forward(req,resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            Collection<CourseType>  courseTypes = CourseTypeDao.getInstance().findAll();
            req.setAttribute("courseTypes",courseTypes);
            StringBuilder conditionSb =  new StringBuilder();
            String title = req.getParameter("title");
            conditionSb.append(" title like '%").append(title).append("%'");
            int courseTypeId = Helper.getIdFromRequest(req,"courseTypeId");
            if(0!=(courseTypeId)){
                conditionSb.append(" and");
                conditionSb.append(" courseType_id= ").append(courseTypeId);
            }
            Student student =(Student) req.getSession().getAttribute("student");
            Collection<CourseSelection> courseSelections = CourseSelectionService.getCourseSelectionService().findByStudentUsername(student.getUsername());
            Collection<Course> courses = CourseService.getInstance().findAll(conditionSb.toString());
            req.setAttribute("courseSelections",courseSelections);
            req.setAttribute("courses",courses);
            req.getRequestDispatcher("/pages/student/studentSelection.jsp").forward(req,resp);
        }catch (SQLException e) {
            req.setAttribute("message","结果查询失败");
            req.getRequestDispatcher("/pages/error.jsp").forward(req,resp);
        }
    }
}
