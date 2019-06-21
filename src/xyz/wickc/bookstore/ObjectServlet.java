package xyz.wickc.bookstore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class ObjectServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mehtodStr = req.getParameter("m");

        Class classObj = this.getClass();

        Method method;
        try {
            if (mehtodStr == null){
                throw new RuntimeException("参数缺失!");
            }
            method = classObj.getDeclaredMethod(mehtodStr, HttpServletRequest.class, HttpServletResponse.class);
            method.setAccessible(false);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("找不到指定的方法!");
        }

        try {
            method.invoke(this,req,resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
