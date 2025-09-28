package com.t2404e.wellcometojavaweb.controller;

import com.t2404e.wellcometojavaweb.entity.Product;
import com.t2404e.wellcometojavaweb.repository.MySQLProductRepository;
import com.t2404e.wellcometojavaweb.repository.ProductRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class ProductList extends HttpServlet {
    ProductRepository repo = new MySQLProductRepository();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword =  req.getParameter("keyword");
        String sort = req.getParameter("sort");
        String order = req.getParameter("order");

        List<Product> products = repo.findAll(keyword, sort, order);
        req.setAttribute("products", products);
        req.getRequestDispatcher("/admin/product/list.jsp").forward(req, resp);
    }
}
