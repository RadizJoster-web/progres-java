package com.budiluhur.catalog_product_rest_api.repository;

import org.springframework.stereotype.Repository;
import com.budiluhur.catalog_product_rest_api.exception.ProductNotFoundException;
import com.budiluhur.catalog_product_rest_api.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository 
public class ProductRepository {

    private final List<Product> productList = new ArrayList<>();

    public ProductRepository() {
        productList.add(new Product("PRD-01", "Keyboard Mechanical", 450000.0));
        productList.add(new Product("PRD-02", "Mouse Wireless Silent", 175000.0));
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public List<Product> findAll() {
        return productList;
    }

    public Product findById(String id) throws ProductNotFoundException {
        for (Product p : productList) {
            if (p.getId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        throw new ProductNotFoundException("Produk dengan ID '" + id + "' tidak ditemukan!");
    }

    public boolean deleteById(String id) throws ProductNotFoundException {
        Product existingProduct = findById(id);
        return productList.remove(existingProduct);
    }

    public Product updateProduct(String id, Product updatedProduct) throws ProductNotFoundException {
        Product existingProduct = findById(id);
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        return existingProduct;
    }

    // Mini Challenge: Pencarian Produk berdasarkan nama
    public List<Product> searchByName(String keyword) {
        return productList.stream()
                .filter(p -> p.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
}