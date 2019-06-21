package xyz.wickc.bookstore.filter;

import xyz.wickc.bookstore.user.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "MainFilter",urlPatterns = "/jsps/*")
public class MainFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();

        session.setMaxInactiveInterval(1800);

        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        session.isNew();
        User user = (User) session.getAttribute("user");

        if (user == null){
            req.setAttribute("msg","您请先登陆后再访问!");
            req.getRequestDispatcher("/user/login.jsp").include(req,resp);
        }else if(!user.getState()){
            req.setAttribute("msg","请先激活你的账号后在进行访问!");
            req.getRequestDispatcher("/jsps/msg.jsp").include(req,resp);
        }else if (req.getHeader("referer") == null || !req.getHeader("referer").startsWith("http://" + req.getServerName() + ":" + req.getServerPort())){
            req.setAttribute("error","你找的地方不存在( 骗子)");
            req.getRequestDispatcher("/error/error.jsp").include(req,resp);
        }else{
            filterChain.doFilter(req,resp);
        }
//        filterChain.doFilter(req,resp);
    }

    @Override
    public void destroy() {

    }
}