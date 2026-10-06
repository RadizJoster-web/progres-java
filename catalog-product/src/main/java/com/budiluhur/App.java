package com.budiluhur;

import com.budiluhur.catalog.model.Product;
import com.budiluhur.catalog.repository.ProductRepository;
import com.budiluhur.exception.ProductNotFoundException;

import com.budiluhur.catalog.servlet.ProductServlet;
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
        ctx.addServletMappingDecoded("/products", "ProductServlet");

        System.out.println("Sistem Berhasil " + "Berjalan di: " + "http://localhost:8080/products");
        tomcat.start();
        tomcat.getServer().wait();

    }

}
