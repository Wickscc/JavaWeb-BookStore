package xyz.wickc.bookstore.user.web.servlet;

import com.alibaba.fastjson.JSONObject;
import xyz.wickc.bookstore.ObjectServlet;
import xyz.wickc.bookstore.cart.domain.Cart;
import xyz.wickc.bookstore.user.domain.User;
import xyz.wickc.bookstore.user.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "UserServlet", urlPatterns = {"/Servlet/UserServlet"})
public class UserServlet extends ObjectServlet {
    private UserService service = new UserService();

    // 登录方法,将Dao传递过来的User对象放到Session中!
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        // 从提交表单中,获取Username与 Password
        String username = new String (req.getParameter("username").getBytes("ISO-8859-1"),"UTF-8");
        String password = new String (req.getParameter("password").getBytes("ISO-8859-1"),"UTF-8");
        HttpSession session = req.getSession();

        // 调用Service中的Login方法查找指定Username与Password的用户对象!
        // 登录需要将User对象放入到Session域中!
        try{
            User user = service.login(username,password);
            session.setAttribute("user",user);
        }catch (Exception e){
            req.setAttribute("msg",e.getMessage());
            req.getRequestDispatcher("/user/login.jsp").include(req,resp);
            return;
        }

        // 跳转到msg.jsp中,并且定时跳转到主页面中!
        resp.setHeader("Refresh","3,url=" + req.getContextPath() + "/jsps/main.jsp");
        req.setAttribute("msg","登陆成功,等待 3 秒后跳转到主页!");
        req.getRequestDispatcher("/jsps/msg.jsp").forward(req,resp);
    }
    // 注册方法
    public void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        // 获取表单数据!
        String username = new String (req.getParameter("username").getBytes("ISO-8859-1"),"UTF-8");
        String password = new String (req.getParameter("password").getBytes("ISO-8859-1"),"UTF-8");
        String email = new String (req.getParameter("email").getBytes("ISO-8859-1"),"UTF-8");

        // 判断是否重复,如果重复着跳转到msg中!
        if (service.isHasEmail(email) || service.isHasUsername(username)){
            req.setAttribute("msg","此用户已经注册,请勿重复注册!");
            req.getRequestDispatcher("/jsps/msg.jsp").include(req,resp);
            return;
        }

        // 随机生成一个Code与Cid,UUID实现
        String code = UUID.randomUUID().toString().replace("-","") + UUID.randomUUID().toString().replace("-","");
        String cid = UUID.randomUUID().toString().replace("-","");
        User user = new User(cid,username,password,email,code,false);

        // 调用Service中的注册方法!
        try{
            service.regist(user);
        }catch (Exception e){
            req.setAttribute("msg",e.getMessage());
            req.getRequestDispatcher("/jsps/msg.jsp").include(req,resp);
            return;
        }

        req.setAttribute("msg","注册成功,请检查邮箱中的激活邮件!");
        req.getRequestDispatcher("/jsps/msg.jsp").include(req,resp);
    }
    // 退出登录 将SESSION销毁!
    public void quite(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        session.invalidate();

        req.setAttribute("msg","您已成功退出");
        req.getRequestDispatcher("/user/login.jsp").include(req,resp);
    }
    // 激活账号
    public void active(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        String code = req.getParameter("code");

        if (code == null || code.isEmpty()){
            req.setAttribute("msg","参数缺失");
            req.getRequestDispatcher("/jsps/msg.jsp").include(req,resp);
            return;
        }

        try {
            service.active(code);
        } catch (NullPointerException e){
            req.setAttribute("msg","参数有误,有可能是链接错误!");
            req.getRequestDispatcher("/jsps/msg.jsp").include(req,resp);
            return;
        } catch (Exception e) {
            req.setAttribute("msg",e.getMessage());
            req.getRequestDispatcher("/jsps/msg.jsp").include(req,resp);
            return;
        }

        // 在激活账号时,需要将Session内的User对象的激活状态进行更新,要么就进不去!
        User user = (User) session.getAttribute("user");

        if (user != null){
            user.setState(true);
        }

        req.setAttribute("msg","激活成功,欢迎使用!");
        req.getRequestDispatcher("/jsps/msg.jsp").include(req,resp);
    }
    // AJAX 接口调用的接口,注册中的错误信息会直接通过AJAX发送到页面上!
    public void isHasUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String username = new String (req.getParameter("username").getBytes("ISO-8859-1"),"UTF-8");

        boolean isHasUsername = service.isHasUsername(username);
        JSONObject jsonObject = new JSONObject();

        if (isHasUsername){
            jsonObject.put("msg","此用户名已经被注册了!");
            jsonObject.put("ok",false);
        }else{
            jsonObject.put("msg","此用户名还没有被注册 !!!");
            jsonObject.put("ok",true);
        }

        resp.getWriter().print(jsonObject.toJSONString());
    }
    // AJAX 接口
    public void isHasEmail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String email = req.getParameter("email");

        boolean isHasEmail = service.isHasEmail(email);
        JSONObject jsonObject = new JSONObject();

        if(isHasEmail){
            jsonObject.put("msg","此邮箱已经被注册了!");
            jsonObject.put("ok",false);
        }else{
            jsonObject.put("msg","此邮箱还没有被注册 !!!");
            jsonObject.put("ok",true);
        }

        resp.getWriter().print(jsonObject.toJSONString());
    }
}