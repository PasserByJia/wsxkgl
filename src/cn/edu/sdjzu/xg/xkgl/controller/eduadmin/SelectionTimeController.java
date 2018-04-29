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
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/selectionTime")
public class SelectionTimeController  extends HttpServlet {
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        try{
            //获得数据库中当前系统选课开放时间
            OpenPeriod openPeriod =OpenPeriodService.getInstance().findAll(1);
            //在请求对象中设置开始时间
            request.setAttribute("startDate",openPeriod.getStartTime());
            //在请求对象中设置结束时间
            request.setAttribute("endDate",openPeriod.getEndTime());
            //请求转发到指定页面
            request.getRequestDispatcher("/pages/eduadmin/selection/selectionTime.jsp").forward(request,response);
        }catch (SQLException e){
            //将错误信息存储到request的message属性中
            request.setAttribute("message","获取选课时间异常");
            //请求转发到指定页面
            request.getRequestDispatcher("/pages/error.jsp").forward(request,response);
        }
    }
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws  ServletException, IOException {
        //从前台获取设置的选课开始时间
        String startDate = request.getParameter("startDate")
                +" "
                +request.getParameter("startHour")
                +":"
                + request.getParameter("startMin")
                +":00";
        //从前台获取设置的选课结束时间
        String endDate = request.getParameter("endDate")
                +" "
                +request.getParameter("endHour")
                +":"
                + request.getParameter("endMin")
                +":00";
        //将String类型的选课开始时间转为Timestamp类型
        Timestamp startTimestamp =  Timestamp.valueOf(startDate);
        //将String类型的选课结束时间转为Timestamp类型
        Timestamp endTimestamp =  Timestamp.valueOf(endDate);
        try {
            //创建相应的OpenPeriod对象
            OpenPeriod openPeriod = new OpenPeriod(1,startTimestamp,endTimestamp);
            //更新openPeriod对象的相应信息
            OpenPeriodService.getInstance().update(openPeriod);
            //重定向到指定页面
            response.sendRedirect("/selectionTime");
        } catch (SQLException e) {
            //将错误信息存储到request的message属性中
            request.setAttribute("message","添加选课时间失败");
            //请求转发到指定页面
            request.getRequestDispatcher("/pages/error.jsp").forward(request,response);
        }
    }
}
