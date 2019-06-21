package xyz.wickc.bookstore.category.web.servlet;

import com.alibaba.fastjson.JSONObject;
import xyz.wickc.bookstore.ObjectServlet;
import xyz.wickc.bookstore.category.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CategoryServlet", urlPatterns = {"/Servlet/CategoryServlet"})
public class CategoryServlet extends ObjectServlet {
    private CategoryService service = new CategoryService();
    // AJAX 接口,返回JSON数据
    public void allCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("CategoryList",service.getAllCategory());
        resp.getWriter().print(jsonObject.toJSONString());
    }
}