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
        //获取请求参数
        String title = request.getParameter("title");
        String text= request.getParameter("text");
        Date date = new Date(System.currentTimeMillis());
        //获取会话对象
        HttpSession httpSession = request.getSession();
        //获取EduAdmin对象
        EduAdmin eduAdmin = (EduAdmin) httpSession.getAttribute("eduadmin");
        //创建notice对象
        Notice noticeToAdd = new Notice(title,text,date,eduAdmin);
        try {
            //增加消息
            NoticeService.getInstance().add(noticeToAdd);
            //重定向到指定页面
            response.sendRedirect("/noticeControllerEdu");
        } catch (SQLException e) {
            //将错误信息存到request的message属性，
            request.setAttribute("message","添加通知失败");
            //重定向到指定页面
            request.getRequestDispatcher("/pages/error.jsp").forward(request,response);
        }


    }
}
