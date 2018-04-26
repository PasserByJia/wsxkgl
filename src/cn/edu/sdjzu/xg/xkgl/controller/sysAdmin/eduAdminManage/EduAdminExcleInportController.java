package cn.edu.sdjzu.xg.xkgl.controller.sysAdmin.eduAdminManage;

import util.ExcelInportEduadmin;
import util.FileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/eduAdminExcleInput")
public class EduAdminExcleInportController extends HttpServlet {
//    private File tmpDir = null;
//    private  String fileName = null;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filename =  FileUpload.fileUpload(request);
        ExcelInportEduadmin.inportEduadmin(filename,request,response);
    }

}
