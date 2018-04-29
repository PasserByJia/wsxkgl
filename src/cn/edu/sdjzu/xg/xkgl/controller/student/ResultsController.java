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

//将此类声明为Servlet,resuletsConntroller是该类的映射地址
@WebServlet("/resultsController")
public class ResultsController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //声明引用
        Collection<CourseSelection> courseSelections = null;
        //在session对象获得student属性值
        Student student = (Student) req.getSession().getAttribute("student");
        try {
            //获得student_id对应的courseSelection对象
            courseSelections = CourseSelectionDao.getInstance().findByStudentId(student.getId());
            //遍历输出选课结果
            for (CourseSelection cou:courseSelections) {
                System.out.println(cou);
            }
        } catch (SQLException e) {
            //异常处理，把错误信息保存在request的message属性中，请求转发到error.jsp
            req.setAttribute("message","查看选课结果出错");
            req.getRequestDispatcher("/pages/error.jsp").forward(req,resp);
        }
        //在请求对象中设置courseSelections属性值
        req.setAttribute("courseSelections",courseSelections);
        //请求转发到selectionResults.jsp
        req.getRequestDispatcher("/pages/student/selectionResults.jsp").forward(req,resp);
    }
}
