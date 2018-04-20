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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/selectionTime")
public class SelectionTimeController  extends HttpServlet {
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/eduadmin/selection/selectionTime.jsp").forward(request,response);
    }
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws  ServletException, IOException {
        StringBuilder beginTime=new StringBuilder();
        beginTime.append(request.getParameter("beginYear")+"/"+request.getParameter("beginMonth")+"/").
                append(request.getParameter("beginDay")+" ").append(request.getParameter("beginHour")+":"+request.getParameter("beginMinute"));
        StringBuilder endTime=new StringBuilder();
        endTime.append(request.getParameter("endYear")+"/"+request.getParameter("endMonth")+"/").append(request.getParameter("endDay")+" ")
                .append(request.getParameter("endHour")+":"+request.getParameter("endMinute"));

        DateFormat df2= new SimpleDateFormat("yyyy/MM/dd HH:mm");
        try {
            java.util.Date date0 = df2.parse(beginTime.toString());
            java.sql.Date sqlDate1 = new java.sql.Date(date0.getTime());
            java.util.Date date1 = df2.parse(endTime.toString());
            java.sql.Date sqlDate2 = new java.sql.Date(date1.getTime());
            OpenPeriod openPeriod = new OpenPeriod(1,sqlDate1,sqlDate2);
            OpenPeriodService.getInstance().update(openPeriod);
        } catch (SQLException e) {
            request.setAttribute("message","添加选课时间失败");
            request.getRequestDispatcher("/pages/error.jsp").forward(request,response);
        }catch (ParseException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/selectionResultController");
    }
}
