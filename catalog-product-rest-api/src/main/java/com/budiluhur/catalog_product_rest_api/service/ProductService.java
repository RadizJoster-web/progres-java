package com.budiluhur.catalog_product_rest_api.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.budiluhur.catalog_product_rest_api.exception.ProductNotFoundException;
import com.budiluhur.catalog_product_rest_api.model.Product;
import com.budiluhur.catalog_product_rest_api.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final DiscountService discountService;

    public ProductService(ProductRepository productRepository, DiscountService discountService) {
        this.productRepository = productRepository;
        this.discountService = discountService;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(String id) throws ProductNotFoundException {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        productRepository.addProduct(product);
        return product;
    }

    public Product updateProduct(String id, Product product) throws ProductNotFoundException {
        return productRepository.updateProduct(id, product);
    }

    public boolean deleteProduct(String id) throws ProductNotFoundException {
        return productRepository.deleteById(id);
    }

    public double getDiscountedProductPrice(String id, double discount) throws ProductNotFoundException {
        Product product = getProductById(id);
        return discountService.calculateDiscountedPrice(product.getPrice(), discount);
    }

    public List<Product> searchProducts(String keyword) {
        return productRepository.searchByName(keyword);
    }
}