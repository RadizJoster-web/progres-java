package com.budiluhur.catalog.servlet;

import com.budiluhur.catalog.model.Product;
import com.budiluhur.catalog.repository.ProductRepository;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    private ProductRepository productRepository;

    @Override 
    public void init() {
        productRepository = new ProductRepository();

        productRepository.addProduct(new Product("PRD-01", "Keyboard", 200000.0));
        productRepository.addProduct(new Product("PRD-02", "Mouse", 135000.0));
        productRepository.addProduct(new Product("PRD-03", "Monitor 240Hz oled", 1500000.0));
    }

    @Override 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        List<Product> products = productRepository.findAll();

        out.println("<html>");
        out.println("<head><title>Katalog "
         + "Produk Web</title></head>");
        out.println("<body>");
        out.println("<h2>=== DAFTAR KATALOG "
         + "PRODUK (WEB) ===</h2>");
        out.println("<table border='1' "
         + "cellpadding='8'>");
        out.println("<tr><th>ID Produk</th>"
         + "<th>Nama Produk</th><th>Harga "
         + "Satuan</th></tr>");
        for (Product p : products) {
            out.println("<tr>");
         out.println("<td>" + p.getId()
         + "</td>");
         out.println("<td>" + p.getName()
         + "</td>");
         out.println("<td>Rp" + p.getPrice()
         + "</td>");
         out.println("</tr>");
        }
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");

        try {
            String id = request.getParameter("id"); 

            if (id != null) {
                Product product = productRepository.findById(id);
                out.println("<h3>Detail Produk</h3>");
                out.println("<p>ID: " + product.getId() + "</p>");
                out.println("<p>Nama: " + product.getName() + "</p>");
                out.println("<p>Harga: Rp" + product.getPrice() + "</p>");
            } else {
                out.println("<p>Parameter 'id' tidak ditemukan.</p>");
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Terjadi kesalahan saat memproses permintaan");
        }
    }
}


