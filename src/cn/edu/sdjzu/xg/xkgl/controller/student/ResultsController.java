package cn.edu.sdjzu.xg.xkgl.controller.student;

import cn.edu.sdjzu.xg.xkgl.dao.CourseSelectionDao;
import cn.edu.sdjzu.xg.xkgl.dao.StudentDao;
import cn.edu.sdjzu.xg.xkgl.domain.Course;
import cn.edu.sdjzu.xg.xkgl.domain.CourseSelection;
import cn.edu.sdjzu.xg.xkgl.domain.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

@WebServlet("/resultsController")
public class ResultsController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<CourseSelection> courseSelections = null;
        Student student = (Student) req.getSession().getAttribute("student");
        try {
            courseSelections = CourseSelectionDao.getInstance().findByStudentId(student.getId());
            for (CourseSelection cou:courseSelections) {
                System.out.println(cou);
            }
        } catch (SQLException e) {
            req.setAttribute("message","查看选课结果出错");
            req.getRequestDispatcher("/pages/error.jsp").forward(req,resp);
        }
        req.setAttribute("courseSelections",courseSelections);
        req.getRequestDispatcher("/pages/student/selectionResults.jsp").forward(req,resp);
    }
}
