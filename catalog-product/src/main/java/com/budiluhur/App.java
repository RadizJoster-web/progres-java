package com.budiluhur;

import com.budiluhur.catalog.servlet.ProductServlet;
import com.budiluhur.catalog.servlet.ProductByIdServlet;
import com.budiluhur.catalog.servlet.CategoryServlet;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Menjalankan Embeded " + "Tomcat Server...");

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();
        Context ctx = tomcat.addContext("", new File(".").getAbsolutePath());

        Tomcat.addServlet(ctx, "ProductServlet", new ProductServlet());
        Tomcat.addServlet(ctx, "ProductByIdServlet", new ProductByIdServlet());
        Tomcat.addServlet(ctx, "CategoryServlet", new CategoryServlet());   
        
        ctx.addServletMappingDecoded("/products", "ProductServlet");
        ctx.addServletMappingDecoded("/product", "ProductByIdServlet");
        ctx.addServletMappingDecoded("/categories", "CategoryServlet");

        System.out.println("Daftar produk: http://localhost:8080/products");
        System.out.println("Detail produk: http://localhost:8080/product?id=PRD-01");
        System.out.println("Daftar kategori: http://localhost:8080/categories");
        tomcat.start();
        tomcat.getServer().wait();

    }

}
