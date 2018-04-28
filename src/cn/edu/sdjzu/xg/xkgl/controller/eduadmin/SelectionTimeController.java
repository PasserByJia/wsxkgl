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
        //请求转发到指定页面
        request.getRequestDispatcher("/pages/eduadmin/selection/selectionTime.jsp").forward(request,response);
    }
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws  ServletException, IOException {
        //创建StringBuilder对象
        StringBuilder beginTime=new StringBuilder();
        //获取相应的参数，并追加到创建的StringBuilder对象
        beginTime.append(request.getParameter("beginYear")+"/"+request.getParameter("beginMonth")+"/").
                append(request.getParameter("beginDay")+" ").append(request.getParameter("beginHour")+":"+request.getParameter("beginMinute"));
        StringBuilder endTime=new StringBuilder();
        endTime.append(request.getParameter("endYear")+"/"+request.getParameter("endMonth")+"/").append(request.getParameter("endDay")+" ")
                .append(request.getParameter("endHour")+":"+request.getParameter("endMinute"));
        //创建日期格式对象df2
        DateFormat df2= new SimpleDateFormat("yyyy/MM/dd HH:mm");
        try {
            //string类型日期转化成Date类型
            java.util.Date date0 = df2.parse(beginTime.toString());
            //根据获取的相应参数创建Date对象
            Date sqlDate1 = new Date(date0.getTime());
            java.util.Date date1 = df2.parse(endTime.toString());
            Date sqlDate2 = new Date(date1.getTime());
            //创建相应的OpenPeriod对象
            OpenPeriod openPeriod = new OpenPeriod(1,sqlDate1,sqlDate2);
            //更新openPeriod对象的相应信息
            OpenPeriodService.getInstance().update(openPeriod);
            //重定向到指定页面
            response.sendRedirect("/selectionResultController");
        } catch (SQLException e) {
            //将错误信息存储到request的message属性中
            request.setAttribute("message","添加选课时间失败");
            //请求转发到指定页面
            request.getRequestDispatcher("/pages/error.jsp").forward(request,response);
        }catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
