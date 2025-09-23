package com.t2404e.wellcometojavaweb.controller;

import com.t2404e.wellcometojavaweb.entity.RegisterInfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Register extends HttpServlet {
    private static final String VIEW = "/WEB-INF/auth/register.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Forward to the JSP that shows the registration form
        req.getRequestDispatcher(VIEW).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 1. Ensure we read request body as UTF-8 (important for non-ASCII)
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());

        // 2. Read form fields
        String username = safe(req.getParameter("username"));
        String password = safe(req.getParameter("password"));
        String email = safe(req.getParameter("email"));
        String confirmPassword = safe(req.getParameter("confirmPassword"));
        HashMap<String, String> errors = new HashMap<>();

        // 3. Validate input
        if (username == null || username.isEmpty()) {
            errors.put("username","Username must not be empty.");
        } else if (!username.matches("^[A-Za-z0-9_]{3,20}$")) {
            errors.put("username","Username must contain only letters/digits/_ and be 3–20 characters long.");
        }

        if (email == null || email.isEmpty()) {
            errors.put("email","Email must not be empty.");
        } else if (!email.contains("@")) {
            errors.put("email","Invalid email address.");
        }

        if (password == null || password.isEmpty()) {
            errors.put("password","Password must not be empty.");
        } else if (password.length() < 6) {
            errors.put("password","Password must be at least 6 characters long.");
        }

        if (confirmPassword == null || !password.equals(confirmPassword)) {
            errors.put("password","Passwords do not match.");
        }

        // 4. Keep username to re-fill the form after a failed submit
        RegisterInfo registerInfo = new RegisterInfo(username, email, password, confirmPassword);

        if (!errors.isEmpty()) {
            // 5. Fail: send error list back to JSP
            req.setAttribute("errors", errors);
            req.setAttribute("registerInfo", registerInfo);
            req.getRequestDispatcher(VIEW).forward(req, resp);
        } else {
            resp.getWriter().write("Register successful.");
        }
    }

    private static String safe(String str) {
        return str == null ? "" : str;
    }
}
