package filter;

import cn.edu.sdjzu.xg.xkgl.domain.OpenPeriod;
import cn.edu.sdjzu.xg.xkgl.service.OpenPeriodService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;

@WebFilter(filterName = "SelectFilter",urlPatterns = {"/selectionController"})
public class OpenperiodFilter implements Filter {
    @Override
    public void destroy(){}
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        try{
           //获取当前系统选课时间对象
            OpenPeriod openPeriod = OpenPeriodService.getInstance().findAll(1);
            //获取选课开始时间
            Timestamp startTimestamp  = openPeriod.getStartTime();
            //获取选课结束时间
            Timestamp endTimestamp  = openPeriod.getEndTime();
            //将时间转换成Date类型以便比较,并获取当前系统时间
            Date startDate = new Date(startTimestamp.getTime());
            Date endDate = new Date(endTimestamp.getTime());
            Date nowDate = new Date(System.currentTimeMillis());
            //如果当前时间不在选课范围内转向开课未开始页面
            if(nowDate.before(startDate) || endDate.before(nowDate)){
                //将错误信息存储到request的message属性中
                req.setAttribute("message","选课未开放!");
                //在请求对象中设置开始时间
                req.setAttribute("startDate",openPeriod.getStartTime());
                //在请求对象中设置结束时间
                req.setAttribute("endDate",openPeriod.getEndTime());
                //请求转发到指定页面
                req.getRequestDispatcher("/pages/outOfOpeneriod.jsp").forward(req,resp);
            }else {
                chain.doFilter(req,resp);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
