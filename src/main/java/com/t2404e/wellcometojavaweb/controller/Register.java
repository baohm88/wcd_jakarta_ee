package com.t2404e.wellcometojavaweb.controller;

import com.t2404e.wellcometojavaweb.entity.Account;
import com.t2404e.wellcometojavaweb.entity.RegisterInfo;
import com.t2404e.wellcometojavaweb.repository.AccountRepository;
import com.t2404e.wellcometojavaweb.repository.MySQLAccountRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class Register extends HttpServlet {
    private static final String VIEW = "/WEB-INF/auth/register.jsp";

    // Repository để thao tác DB
    private final AccountRepository accountRepo = new MySQLAccountRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(VIEW).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());

        // Lấy dữ liệu từ form
        String username = safe(req.getParameter("username"));
        String password = safe(req.getParameter("password"));
        String email = safe(req.getParameter("email"));
        String confirmPassword = safe(req.getParameter("confirmPassword"));

        RegisterInfo info = new RegisterInfo(username, email, password, confirmPassword);
        HashMap<String, String> errors = validate(info);

        if (!errors.isEmpty()) {
            // Nếu có lỗi → trả về form kèm thông báo
            req.setAttribute("errors", errors);
            req.setAttribute("info", info);
            req.getRequestDispatcher(VIEW).forward(req, resp);
        } else {
            try {
                // Tạo Account và lưu vào DB
                Account acc = new Account(username, email, password, 1); // status=1 = active
                accountRepo.save(acc);

                resp.setContentType("text/html; charset=UTF-8");
                resp.getWriter().write("Register successful. New account created!");
            } catch (Exception e) {
                // Nếu lỗi DB (VD: username trùng)
                errors.put("username", "Username already exists.");
                req.setAttribute("errors", errors);
                req.setAttribute("info", info);
                req.getRequestDispatcher(VIEW).forward(req, resp);
            }
        }
    }

    private static String safe(String str) {
        return str == null ? "" : str.trim();
    }

    private HashMap<String, String> validate(RegisterInfo info) {
        HashMap<String, String> errors = new HashMap<>();

        if (info.getUsername().isEmpty()) {
            errors.put("username", "Username must not be empty.");
        }
        if (info.getEmail().isEmpty()) {
            errors.put("email", "Email must not be empty.");
        } else if (!info.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            errors.put("email", "Email format is invalid.");
        }
        if (info.getPassword().isEmpty()) {
            errors.put("password", "Password must not be empty.");
        }
        if (info.getConfirmPassword().isEmpty() || !info.getConfirmPassword().equals(info.getPassword())) {
            errors.put("confirmPassword", "Passwords do not match.");
        }

        return errors;
    }
}