package cn.edu.sdjzu.xg.xkgl.controller.sysAdmin.teacherManage;

import util.ExcelInportTeacher;
import util.FileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/teacherExcleInput")

public  class TeacherExcleInportController extends HttpServlet {

    private  String fileName = null;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String filename =  FileUpload.fileUpload(request);
        ExcelInportTeacher.inportTeacher(filename,request,response);
    }


}
