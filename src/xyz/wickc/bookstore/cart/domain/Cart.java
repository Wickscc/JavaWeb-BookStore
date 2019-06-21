package xyz.wickc.bookstore.cart.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Cart implements Serializable {
    // 存放图书的Map集合,其中的 Key为Bid,CartItem包含图书对象与个数!
    private Map<String,CartItem> map;

    public Cart() {
        map = new HashMap<>();
    }

    public void addToCart(CartItem cartItem){
        String bid = cartItem.getBook().getBid();
        map.put(bid,cartItem);
    }

    public Map<String, CartItem> getMap() {
        return map;
    }

    public void remove(int bid){
        Object object = map.remove(String.valueOf(bid));

        if (object == null){
            throw new RuntimeException("删除失败!");
        }
    }

    public void removeAll(){
        map.clear();
    }
}
