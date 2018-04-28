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

@WebServlet("/noticeController")
public class NoticeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //创建集合对象
        Collection<Notice> notices = null;
        try {
            //获取所有的Notice 对象
            notices = NoticeService.getInstance().findAll();
        } catch (SQLException e) {
            //将message属性值e.getMessage()获取到的异常信息存储到request对象中
            req.setAttribute("message","获取通知失败");
            //请求重定向到指定页面
            req.getRequestDispatcher("/pages/error.jsp").forward(req,resp);
        }
        //将notices属性值存储到request对象中
        req.setAttribute("notices",notices);
        //请求重定向到指定页面
        req.getRequestDispatcher("/pages/notice.jsp").forward(req,resp);
    }
}

