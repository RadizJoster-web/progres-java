package com.budiluhur.catalog.repository;

import com.budiluhur.catalog.model.Category;
import java.util.*;

public class CategoryRepository {
    private List<Category> categoryList = new ArrayList<>();
    
    public void addCategory(Category category) {
        categoryList.add(category);
    }

    public List<Category> findAll() {
        return categoryList;
    }
}
