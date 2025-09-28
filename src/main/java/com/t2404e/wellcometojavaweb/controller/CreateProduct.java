package com.t2404e.wellcometojavaweb.controller;
import com.t2404e.wellcometojavaweb.entity.Product;
import com.t2404e.wellcometojavaweb.entity.helper.ProductEnum;
import com.t2404e.wellcometojavaweb.repository.MySQLProductRepository;
import com.t2404e.wellcometojavaweb.repository.ProductRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CreateProduct extends HttpServlet {
    ProductRepository productRepository = new MySQLProductRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/admin/product/form.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String content = req.getParameter("content");
        double price =  Double.parseDouble(req.getParameter("price"));
        String thumbnail = req.getParameter("thumbnail");
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setContent(content);
        product.setThumbnail(thumbnail);
        product.setPrice(price);
        product.setStatus(ProductEnum.ACTIVE);
        productRepository.save(product);
        resp.getWriter().println("success");
    }
}
