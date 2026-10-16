package com.budiluhur.catalog_product_rest_api.service;

import org.springframework.stereotype.Service;

@Service
public class DiscountService {
    public double calculateDiscountedPrice(double originalPrice, double discountPercentage) {
        if (discountPercentage <= 0) {
            return originalPrice;
        }
        double discountAmount = originalPrice * (discountPercentage / 100.0);
        return originalPrice - discountAmount;
    }
}