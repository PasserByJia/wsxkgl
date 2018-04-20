package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "XEncodingFilter",urlPatterns = {"/*"})
public class XEncodingFilter implements Filter{
    @Override
    public void destroy(){}
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        response.setContentType("text/html;charset = UTF8");
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(req, resp);
    }
}
