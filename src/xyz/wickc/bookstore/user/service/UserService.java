package xyz.wickc.bookstore.user.service;

import xyz.wickc.bookstore.user.dao.UserDao;
import xyz.wickc.bookstore.user.domain.User;
import xyz.wickc.emailSand.EmailUtils;

import java.security.GeneralSecurityException;
import java.sql.SQLException;

public class UserService {
    private UserDao dao = new UserDao();
    private EmailUtils emailUtils = new EmailUtils();

    public boolean isHasUsername(String username){
        try {
            return dao.isHasUsername(username);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("内部错误!!!");
        }
    }

    public boolean isHasEmail(String email){
        try {
            return dao.isHasEmail(email);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("内部错误!!!");
        }
    }

    public void regist(User user){
        try {
            dao.regist(user);
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("内部错误! Code : 0x000002");
        }

        new Thread(){
            @Override
            public void run() {
                try {
                    emailUtils.sandEmail(user.getEmail(),"dr.wicks@qq.com","注册信息","<h1>恭喜你,注册成功!</h1><br/><a href=\"http://127.0.0.1:8080/SL_BookStore/Servlet/UserServlet?m=active&code="+user.getCode()+"\">激活链接</a>");
                } catch (Exception e) {
                    System.out.println(e);
                    throw new RuntimeException("内部错误! Code : 0x000003");
                }
            }
        }.start();
    }

    public void active(String code) throws Exception {
        boolean flag;
        try {
            flag = dao.active(code);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("内部错误! Code : 0x000004");
        }catch (NullPointerException e){
            throw new RuntimeException("参数有误!");
        }

        if (!flag){
            throw new Exception("激活失败,有可能是因为链接不正确或者已经激活!");
        }
    }

    // 登录方法
    public User login(String username,String password){
        User user;
        try {
            user = dao.login(username,password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("内部错误! Code : 0x000005");
        }

        if (user == null){
            throw new RuntimeException("登陆失败,用户名或密码错误!");
        }else{
            return user;
        }
    }
}
