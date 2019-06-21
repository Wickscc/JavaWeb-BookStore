package xyz.wickc.bookstore.order.web.servlet;

import com.alibaba.fastjson.JSONObject;
import xyz.wickc.bookstore.ObjectServlet;
import xyz.wickc.bookstore.book.domain.Book;
import xyz.wickc.bookstore.book.service.BookService;
import xyz.wickc.bookstore.cart.domain.Cart;
import xyz.wickc.bookstore.cart.domain.CartItem;
import xyz.wickc.bookstore.category.domain.Category;
import xyz.wickc.bookstore.category.service.CategoryService;
import xyz.wickc.bookstore.order.domain.Order;
import xyz.wickc.bookstore.order.domain.Orderitem;
import xyz.wickc.bookstore.order.service.OrderService;
import xyz.wickc.bookstore.user.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet(name = "OrderServlet", urlPatterns = {"/Servlet/OrderServlet"})
public class OrderServlet extends ObjectServlet {
    private OrderService service = new OrderService();
    private BookService bookService = new BookService();
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void addOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");

        String orderTime = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        String oid = UUID.randomUUID().toString().replace("-","");
        List<Orderitem> orderitemList = createOrderitemList(cart,oid);
        String uid = user.getUid();
        double price = Double.parseDouble(req.getParameter("price"));

        Order order = new Order(oid,orderTime,price,0,uid,orderitemList);

        service.addOrder(order);

//        Map<String,List<Order>> map = (Map<String, List<Order>>) session.getAttribute("orderMap");
//        List<Order> orderList = map.get(uid);

//        orderList.add(order);

        cart.removeAll();

        req.setAttribute("order",order);
        req.getRequestDispatcher("/jsps/order/desc.jsp").include(req,resp);
    }

    public void pay(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();

        String address = new String(req.getParameter("address").getBytes("ISO-8859-1"),"UTF-8");
        String oid = new String(req.getParameter("oid").getBytes("ISO-8859-1"),"UTF-8");

        service.insertAddress(address,oid);

        resp.setHeader("Refresh","3,url=" + req.getContextPath() + "/jsps/main.jsp");
        req.setAttribute("msg","假装你已经付过款了 ...");
        req.getRequestDispatcher("/jsps/msg.jsp").include(req,resp);
    }

    public void myOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        String uid = user.getUid();
        List<Order> orderList = service.getAllOrder(uid);

        req.setAttribute("orderList",orderList);
        req.getRequestDispatcher("/jsps/order/list.jsp").include(req,resp);
    }

    public void oidOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String oid = req.getParameter("oid");
        Order order = service.getAllOrderByOid(oid);

        if (order.getState() != 0){
            resp.setHeader("Refresh","1,url=" + req.getContextPath() + "/Servlet/OrderServlet?m=myOrder");
            req.setAttribute("msg","你已经付过款了,就不要来添乱了吧!");
            req.getRequestDispatcher("/jsps/msg.jsp").include(req,resp);
            return;
        }

        req.setAttribute("order",order);
        req.getRequestDispatcher("/jsps/order/desc.jsp").include(req,resp);
    }

    public void chOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String oid = req.getParameter("oid");
        Order order = service.getAllOrderByOid(oid);

        if (order.getState() == 2){
            resp.setHeader("Refresh","1,url=" + req.getContextPath() + "/Servlet/OrderServlet?m=myOrder");
            req.setAttribute("msg","您已经收货完成了,请不要重复操作");
            req.getRequestDispatcher("/jsps/msg.jsp").include(req,resp);
        }else{
            service.chOrder(oid);
            resp.setHeader("Refresh","1,url=" + req.getContextPath() + "/Servlet/OrderServlet?m=myOrder");
            req.setAttribute("msg","收货成功,感谢您的再次光临!");
            req.getRequestDispatcher("/jsps/msg.jsp").include(req,resp);
        }
    }

    public List<Orderitem> createOrderitemList(Cart cart,String oid){
        List<Orderitem> orderitemList = new ArrayList<>();
        Map<String,CartItem> cartMap = cart.getMap();
        Set<String> keySet = cartMap.keySet();
        for(String key : keySet){
            String iid = UUID.randomUUID().toString().replace("-","");
            CartItem item = cartMap.get(key);
            int bid = Integer.parseInt(item.getBook().getBid());
            int count = item.getCount();
            double subtotal = count * Double.valueOf(item.getBook().getPrice());

            Book book = bookService.getBookByBid(bid);
            orderitemList.add(new Orderitem(iid,count,subtotal,oid,String.valueOf(bid),book));
        }

        return orderitemList;
    }
}