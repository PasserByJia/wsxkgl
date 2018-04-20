package cn.edu.sdjzu.xg.xkgl.controller.eduadmin;

import cn.edu.sdjzu.xg.xkgl.domain.EduAdmin;
import cn.edu.sdjzu.xg.xkgl.domain.Notice;
import cn.edu.sdjzu.xg.xkgl.service.EduAdminService;
import cn.edu.sdjzu.xg.xkgl.service.NoticeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;


@WebServlet("/noticeControllerAdd")
public class NoticeControllerAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String title = request.getParameter("title");
        String text= request.getParameter("text");
        Date date = new Date(System.currentTimeMillis());
        HttpSession httpSession = request.getSession();

        EduAdmin eduAdmin = (EduAdmin) httpSession.getAttribute("eduadmin");
        Notice noticeToAdd = new Notice(title,text,date,eduAdmin);
        try {
            NoticeService.getInstance().add(noticeToAdd);
        } catch (SQLException e) {
            request.setAttribute("message","添加通知失败");
            request.getRequestDispatcher("/pages/error.jsp").forward(request,response);
        }
        response.sendRedirect("/noticeControllerEdu");

    }
}
