package com.t2404e.wellcometojavaweb.controller;

import com.t2404e.wellcometojavaweb.entity.Product;
import com.t2404e.wellcometojavaweb.repository.MySQLProductRepository;
import com.t2404e.wellcometojavaweb.repository.ProductRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
public class EditProduct extends HttpServlet {
    ProductRepository repo = new MySQLProductRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        System.out.println("id:" + idStr);
        if (idStr == null) {
            resp.sendRedirect(req.getContextPath() + "/admin/product/list");
            return;
        }

        try {
            long id = Long.parseLong(idStr);
            Product product = repo.findById(id);
            System.out.println(product);
            if (product == null) {
                resp.sendRedirect(req.getContextPath() + "/admin/product/list?error=not+found");
                return;
            }
            req.setAttribute("product", product);
            req.getRequestDispatcher("/admin/product/form.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/admin/product/list?error=invalid+id");
        }
    }
}
