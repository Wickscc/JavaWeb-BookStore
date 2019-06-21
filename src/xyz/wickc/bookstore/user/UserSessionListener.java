package xyz.wickc.bookstore.user;

import xyz.wickc.bookstore.cart.domain.Cart;
import xyz.wickc.bookstore.order.domain.Order;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@WebListener()
public class UserSessionListener implements HttpSessionAttributeListener,HttpSessionListener {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("Add : " + simpleDateFormat.format(new Date(System.currentTimeMillis())) + "  "+ httpSessionBindingEvent.getName() + ":" + httpSessionBindingEvent.getValue());
        HttpSession session = httpSessionBindingEvent.getSession();

        if(httpSessionBindingEvent.getName().equals("user")){
            session.setAttribute("cart",new Cart());
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("Remove : " + simpleDateFormat.format(new Date(System.currentTimeMillis())) + "  "+ httpSessionBindingEvent.getName() + ":" + httpSessionBindingEvent.getValue());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("Rep : " + simpleDateFormat.format(new Date(System.currentTimeMillis())) + "  "+ httpSessionBindingEvent.getName() + ":" + httpSessionBindingEvent.getValue());
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();

        // 其中的Key为UID Order为订单对象
//        session.setAttribute("orderMap",new HashMap<String, List<Order>>());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }
}
