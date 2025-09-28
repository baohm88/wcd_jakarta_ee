package com.t2404e.wellcometojavaweb.controller;

import com.t2404e.wellcometojavaweb.entity.Product;
import com.t2404e.wellcometojavaweb.repository.MySQLProductRepository;
import com.t2404e.wellcometojavaweb.repository.ProductRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DeleteProduct extends HttpServlet {
    private final ProductRepository repo = new MySQLProductRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            try {
                long id = Long.parseLong(idStr);
                repo.deleteById(id);
            } catch (NumberFormatException e) {
                resp.sendRedirect("products?error=invalid id");
            }
        }
        resp.sendRedirect(req.getContextPath() + "/admin/product/list");
    }
}
