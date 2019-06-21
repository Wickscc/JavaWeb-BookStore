package xyz.wickc.bookstore.category.service;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import xyz.wickc.bookstore.category.dao.CategoryDao;
import xyz.wickc.bookstore.category.domain.Category;

import java.util.List;

public class CategoryService {
    private CategoryDao dao = new CategoryDao();
    public List<Category> getAllCategory(){
        try {
            return dao.getAllCategory();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("内部错误! Code : 0x000011");
        }
    }
}
