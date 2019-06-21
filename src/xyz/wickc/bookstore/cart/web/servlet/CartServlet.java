package xyz.wickc.bookstore.cart.web.servlet;

import xyz.wickc.bookstore.ObjectServlet;
import xyz.wickc.bookstore.book.domain.Book;
import xyz.wickc.bookstore.book.service.BookService;
import xyz.wickc.bookstore.cart.domain.Cart;
import xyz.wickc.bookstore.cart.domain.CartItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "CartServlet", urlPatterns = {"/Servlet/CartServlet"})
public class CartServlet extends ObjectServlet {
    private BookService bookService = new BookService();

    public void appendCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        int bid = 0;
        int len = 0;
        try{
            bid = Integer.parseInt(req.getParameter("bid"));
            len = Integer.parseInt(req.getParameter("count"));
        }catch (Exception e){
            resp.setHeader("Refresh","3,url=" + req.getContextPath() + "/jsps/main.jsp");
            req.setAttribute("msg","参数错误!,等待 3 秒后跳转到主页!");
            req.getRequestDispatcher("/jsps/msg.jsp").forward(req,resp);
            return;
        }

        Book book = bookService.getBookByBid(bid);
        CartItem item = new CartItem(book,len);

        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        cart.addToCart(item);

        resp.setHeader("Refresh","1,url=" + req.getContextPath() + "/jsps/cart/list.jsp");
        req.setAttribute("msg","添加成功");
        req.getRequestDispatcher("/jsps/cart/msg.jsp").include(req,resp);
    }

    public void removeCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        int bid = 0;
        try{
            bid = Integer.parseInt(req.getParameter("bid"));
        }catch (Exception e){
            resp.setHeader("Refresh","3,url=" + req.getContextPath() + "/jsps/main.jsp");
            req.setAttribute("msg","参数错误!,等待 3 秒后跳转到主页!");
            req.getRequestDispatcher("/jsps/msg.jsp").forward(req,resp);
            return;
        }

        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        cart.remove(bid);

        resp.setHeader("Refresh","1,url=" + req.getContextPath() + "/jsps/cart/list.jsp");
        req.setAttribute("msg","删除成功 ! ");
        req.getRequestDispatcher("/jsps/cart/msg.jsp").include(req,resp);
    }

    public void removeAllCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        cart.removeAll();

        resp.setHeader("Refresh","3,url=" + req.getContextPath() + "/jsps/cart/list.jsp");
        req.setAttribute("msg","全部删除成功  ! ");
        req.getRequestDispatcher("/jsps/cart/msg.jsp").include(req,resp);
    }
}