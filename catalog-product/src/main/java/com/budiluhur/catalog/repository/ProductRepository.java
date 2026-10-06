package com.budiluhur.catalog.repository;

import com.budiluhur.catalog.model.Product;
import com.budiluhur.exception.ProductNotFoundException;
import java.util.*;

public class ProductRepository {
    private List<Product> productList = new ArrayList<>();

    public void addProduct(Product product) {
        productList.add(product);
    }

    public List<Product> findAll() {
        return productList;
    }

    public Product findById(String id) throws ProductNotFoundException {
        for (Product p : productList){
            if(p.getId().equalsIgnoreCase(id)) {
                return p;
            }
        }

        throw new ProductNotFoundException("Produk dengan id " + "[" + id + "]" + " tidak ditemukan");
    }

    public boolean deleteById(String id) throws ProductNotFoundException {
        Product p = findById(id);

        if(p != null){
            productList.remove(p);
            return true;
        }else{
            throw new ProductNotFoundException("Prouduk dengan id " + p + " tidak ditemukan");
        }
    }
}
