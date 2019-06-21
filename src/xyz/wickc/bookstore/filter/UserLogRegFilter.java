package xyz.wickc.bookstore.filter;

import xyz.wickc.bookstore.user.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "UserLogRegFilter",urlPatterns = "/user/*")
public class UserLogRegFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();

        User loginUser = (User) session.getAttribute("user");

        if (loginUser == null){
            filterChain.doFilter(req,resp);
            return;
        }else{
            if (req.getServletPath().equals("/user/login.jsp")){
                req.setAttribute("msg","请不要重新登录! 3秒后为您跳转到主页!");
            }else if (req.getServletPath().equals("/user/regist.jsp")){
                req.setAttribute("msg","请您退出后再进行注册! 3秒后为您跳转到主页!");
            }
            resp.setHeader("Refresh","3,url=" + req.getContextPath() + "/jsps/domain.jsp");
            req.getRequestDispatcher("/jsps/msg.jsp").forward(req,resp);
        }
    }

    @Override
    public void destroy() {

    }
}