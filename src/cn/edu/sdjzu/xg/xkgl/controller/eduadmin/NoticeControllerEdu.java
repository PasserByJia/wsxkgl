package cn.edu.sdjzu.xg.xkgl.controller.eduadmin;

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
import java.util.HashSet;

@WebServlet("/noticeControllerEdu")
public class NoticeControllerEdu extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException {
        Collection<Notice> notices = new HashSet<Notice>();
        try {
            //获取所有通知对象，并存储到相应的集合对象中
            notices = NoticeService.getInstance().findAll();
            //设置请求参数notices
            req.setAttribute("notices",notices);
            //请求转发到指定页面
            req.getRequestDispatcher("/pages/eduadmin/notice/notices.jsp").forward(req,resp);
        } catch (SQLException e) {
            //将错误信息存到request的message属性，
            req.setAttribute("message","选课结果查询出错");
            req.getRequestDispatcher("/pages/error.jsp").forward(req,resp);

        }


    }
}
