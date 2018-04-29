package cn.edu.sdjzu.xg.xkgl.controller;

import cn.edu.sdjzu.xg.xkgl.domain.Notice;
import cn.edu.sdjzu.xg.xkgl.service.NoticeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.TreeSet;

//将此类声明为Servlet,checknoticeConntroller是该类的映射地址
@WebServlet("/checknoticeController")
public class ChecknoticeController extends HttpServlet{
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException {
        //获取请求传入的id参数值
        int id = Integer.parseInt(req.getParameter("id"));
        System.out.println(id);
        try {
            //通过id获取对应的notice对象
            Notice notice = NoticeService.getInstance().find(id);
            //在请求对象中设置notice属性
            req.setAttribute("notice",notice);
            //请求转发到detailedNotice.jsp
            req.getRequestDispatcher("pages/detailedNotice.jsp").forward(req,resp);
        } catch (SQLException e) {
            //异常处理，把错误信息保存在request的message属性中，请求转发到error.jsp
            req.setAttribute("message","查看通知详情失败");
            req.getRequestDispatcher("/pages/error.jsp").forward(req,resp);
        }
    }
}
