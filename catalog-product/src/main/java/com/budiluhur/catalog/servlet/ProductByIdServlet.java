package com.budiluhur.catalog.servlet;

import com.budiluhur.catalog.model.Product;
import com.budiluhur.catalog.repository.ProductRepository;
import com.budiluhur.exception.ProductNotFoundException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ProductByIdServlet extends HttpServlet {
    private ProductRepository productRepository;

    @Override 
    public void init() {
        productRepository = new ProductRepository();

        productRepository.addProduct(new Product("PRD-01", "Keyboard", 200000.0));
        productRepository.addProduct(new Product("PRD-02", "Mouse", 135000.0));
        productRepository.addProduct(new Product("PRD-03", "Monitor 240Hz oled", 1500000.0));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");

        if (id == null || id.isBlank()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parameter 'id' tidak ditemukan");
            return;
        }

        Product product;
        try {
            product = productRepository.findById(id);
        } catch (ProductNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
            return;
        }

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Detail Produk</title></head>");
        out.println("<body>");
        out.println("<h3>Detail Produk</h3>");
        out.println("<p>ID: " + product.getId() + "</p>");
        out.println("<p>Nama: " + product.getName() + "</p>");
        out.println("<p>Harga: Rp" + product.getPrice() + "</p>");
        out.println("</body>");
        out.println("</html>");
    }
}
