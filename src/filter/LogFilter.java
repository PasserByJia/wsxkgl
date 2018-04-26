package filter;

import cn.edu.sdjzu.xg16.bysj.domain.User;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.text.SimpleDateFormat;
import java.util.*;

@WebFilter(filterName = "MessageFilter",urlPatterns = {"/*"})
public class LogFilter implements Filter {
    @Override
    public void destroy(){}
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            request.getRequestDispatcher("/login/loginController.do").forward(request,resp);
        }
        else {
            String name = user.getTeacher().getName();
            String uri = request.getRequestURI();
            System.out.println(loginTime + " ," +name+ ","+uri+",");
            chain.doFilter(request,resp);
        }
    }
}

