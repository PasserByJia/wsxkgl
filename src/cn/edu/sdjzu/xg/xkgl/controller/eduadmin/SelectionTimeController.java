package cn.edu.sdjzu.xg.xkgl.controller.eduadmin;

import cn.edu.sdjzu.xg.xkgl.domain.OpenPeriod;
import cn.edu.sdjzu.xg.xkgl.service.OpenPeriodService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;


@WebServlet("/selectionTime")
public class SelectionTimeController  extends HttpServlet {
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/eduadmin/selection/selectionTime.jsp").forward(request,response);
    }
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws  ServletException, IOException {
        String startDateS = request.getParameter("startDate");
        String startHour = request.getParameter("startHour");
        String startMin = request.getParameter("startMin");
        String start =startDateS;
        String endDateS = request.getParameter("endDate");
        String endHour = request.getParameter("endHour");
        String endMin = request.getParameter("endMin");
        String end =endDateS;
        Date startDate = Date.valueOf(start);
        Date endDate = Date.valueOf(end);
        OpenPeriod openPeriod = new OpenPeriod(1,startDate,endDate);
        try {
            OpenPeriodService.getInstance().update(openPeriod);
        }catch (SQLException e){
            request.setAttribute("message","添加时间出错");
            request.getRequestDispatcher("/pages/error.jsp").forward(request,response);
        }

        response.sendRedirect("/selectionResultController");
    }
}
