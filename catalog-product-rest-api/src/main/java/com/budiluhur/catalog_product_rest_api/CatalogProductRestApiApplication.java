package com.budiluhur.catalog_product_rest_api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.budiluhur.catalog_product_rest_api.model.Product;
import com.budiluhur.catalog_product_rest_api.service.ProductService;

@SpringBootApplication
public class CatalogProductRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogProductRestApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(ProductService productService) {
		return args -> {
			System.out.println("=== SPRING BOOT PRODUCT CATALOG IS RUNNING ===");

			productService.createProduct(new Product("PRD-01", "Monitor Gaming 24 Inch", 2100000.0));
			System.out.println("Daftar Produk di Spring Container:");

			System.out.println("\nINFORMASI HARGA & DISKON");
            String targetId = "PRD-01";
            double discountRate = 10.0;

            Product product = productService.getProductById(targetId);
            double finalPrice = productService.getDiscountedProductPrice(targetId, discountRate);

            System.out.println("[" + product.getId() + "] " + product.getName());
            System.out.println("Harga Normal : Rp" + product.getPrice());
            System.out.println("Diskon       : " + discountRate + "%");
            System.out.println("Harga Akhir  : Rp" + finalPrice);

			productService.getAllProducts().forEach(System.out::println);
		};	
	}
}
