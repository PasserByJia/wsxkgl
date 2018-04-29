package cn.edu.sdjzu.xg.xkgl.controller;

import cn.edu.sdjzu.xg.xkgl.domain.Student;
import cn.edu.sdjzu.xg.xkgl.domain.message.Result;
import cn.edu.sdjzu.xg.xkgl.service.CourseSelectionService;
import cn.edu.sdjzu.xg.xkgl.service.ExportService;
import util.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//将此类声明为Servlet,exportConntroller是该类的映射地址
@WebServlet("/exportController")
public class ExportController extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求传入的cource对象的id参数值
        int course_id = Helper.getIdFromRequest(request,"courseId");
        //List集合用来存放与cource的id相关联的student对象
        List<Student> students = null;
        try{
            //students为名的集合中保存通过cource_id获取与cource的id相关联的student对象
            students= CourseSelectionService.getCourseSelectionService().findStudentByCourseId(course_id);
        }catch (SQLException e){}//异常处理,调用exportException方法
        this.exportExcel(response,request,students);

    }
    //获得ExportService的实例
    ExportService exportService = ExportService.getInstance();
    /**
     * 导出excel
     */
    public Result exportExcel(HttpServletResponse response,
                              HttpServletRequest request,List<Student> studentList) throws IOException {
        //导出excel
        return this.exportService.exportExcel(studentList,response,request);
    }

}
