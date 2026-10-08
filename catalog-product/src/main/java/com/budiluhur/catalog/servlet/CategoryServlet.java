package com.budiluhur.catalog.servlet;

import com.budiluhur.catalog.model.Category;
import com.budiluhur.catalog.repository.CategoryRepository;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CategoryServlet extends HttpServlet {
    private CategoryRepository categoryRepository;

    @Override 
    public void init() {
        categoryRepository = new CategoryRepository();

        categoryRepository.addCategory(new Category("CTG-01", "Monitor"));
        categoryRepository.addCategory(new Category("CTG-02", "PC"));
        categoryRepository.addCategory(new Category("CTG-03", "Meja"));
    }

    @Override 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        List<Category> categories = categoryRepository.findAll();

        out.println("<html>");
        out.println("<head><title>Katalog "
         + "Produk Web</title></head>");
        out.println("<body>");
        out.println("<h2>=== DAFTAR KATALOG "
         + "KATEGORI ===</h2>");
        out.println("<ul>");
        for (Category ctg: categories) {
            out.println("<li>");
            out.println("ID Kategori: " + ctg.getId() + ", Nama Kategori: " + ctg.getName());
            out.println("</li>");
        }
        out.println("</ul>");
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }
}
