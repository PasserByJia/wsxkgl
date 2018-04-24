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

@WebServlet("/exportController")
public class ExportController extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int course_id = Helper.getIdFromRequest(request,"courseId");
        List<Student> students = null;
        try{
            students= CourseSelectionService.getCourseSelectionService().findStudentByCourseId(course_id);
        }catch (SQLException e){}
        this.exportExcel(response,request,students);

    }
    ExportService exportService = ExportService.getInstance();
    /**
     * 导出excel
     * @param
     */
    public Result exportExcel(HttpServletResponse response,
                              HttpServletRequest request,List<Student> studentList) throws IOException {
        //导出excel
        return this.exportService.exportExcel(studentList,response,request);
    }

}
