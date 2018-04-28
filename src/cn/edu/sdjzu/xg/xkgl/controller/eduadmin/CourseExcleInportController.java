package cn.edu.sdjzu.xg.xkgl.controller.eduadmin;

import util.ExcleInportCourse;
import util.FileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/courseExcleInput")
public class CourseExcleInportController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取文件名称
        String filename =  FileUpload.fileUpload(request);
        //execle导入课程
        ExcleInportCourse.inportCourse(filename,request,response);
    }
}
