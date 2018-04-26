package filter;



import cn.edu.sdjzu.xg.xkgl.domain.Student;
import cn.edu.sdjzu.xg.xkgl.domain.User;

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
            request.getRequestDispatcher("/loginController").forward(request,resp);
        } else {
            chain.doFilter(request,resp);
        }
    }
}

