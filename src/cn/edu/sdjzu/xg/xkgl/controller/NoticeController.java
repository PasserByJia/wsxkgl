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
        Collection<Notice> notices = null;
        System.out.println("===========================");
        try {
            notices = NoticeService.getInstance().findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("notices",notices);
        req.getRequestDispatcher("/pages/notice.jsp").forward(req,resp);
    }
}

