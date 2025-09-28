<%@ page import="java.util.Map" %>
<%@ page import="com.t2404e.wellcometojavaweb.entity.RegisterInfo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%
    Map<String, String> errors = (Map<String, String>) request.getAttribute("errors");
    RegisterInfo registerInfo = (RegisterInfo) request.getAttribute("info");
    if (registerInfo == null) registerInfo = new RegisterInfo();
%>

<%!
    // Use fully-qualified type to avoid any compiler quirk
    private String err(java.util.Map<String, String> errs, String key) {
        if (errs == null) return "";
        String v = errs.get(key);
        return v == null ? "" : v;
    }
%>

<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 520px;
            margin: 40px auto;
        }

        form {
            display: grid;
            gap: 12px;
        }

        label {
            display: block;
            font-weight: 600;
        }

        input[type=text], input[type=password], input[type=email] {
            width: 100%;
            padding: 8px;
        }

        .field-error {
            color: #b10000;
            font-size: 13px;
            margin-top: 4px;
        }

        .msg {
            margin: 12px 0;
            padding: 10px;
            border-radius: 6px;
        }

        .msg-err {
            background: #ffecec;
            color: #b10000;
            border: 1px solid #ffb3b3;
        }
    </style>
</head>
<body>
<h1>Register</h1>

<% if (errors != null && !errors.isEmpty()) { %>
<div class="msg msg-err"><strong>Please fix the errors below.</strong></div>
<% } %>

<form action="<%= request.getContextPath() %>/register" method="post">
    <div>
        <label for="username">Username</label>
        <input type="text" name="username" id="username"
               value="<%= registerInfo.getUsername() == null ? "" : registerInfo.getUsername() %>" autofocus>
        <% if (!err(errors, "username").isEmpty()) { %>
        <div class="field-error"><%= err(errors, "username") %>
        </div>
        <% } %>
    </div>

    <div>
        <label for="email">Email</label>
        <input type="email" name="email" id="email"
               value="<%= registerInfo.getEmail() == null ? "" : registerInfo.getEmail() %>">
        <% if (!err(errors, "email").isEmpty()) { %>
        <div class="field-error"><%= err(errors, "email") %>
        </div>
        <% } %>
    </div>

    <div>
        <label for="password">Password</label>
        <input type="password" name="password" id="password">
        <% if (!err(errors, "password").isEmpty()) { %>
        <div class="field-error"><%= err(errors, "password") %>
        </div>
        <% } %>
    </div>

    <div>
        <label for="confirmPassword">Confirm Password</label>
        <input type="password" name="confirmPassword" id="confirmPassword">
        <% if (!err(errors, "confirmPassword").isEmpty()) { %>
        <div class="field-error"><%= err(errors, "confirmPassword") %>
        </div>
        <% } %>
    </div>

    <div>
        <button type="submit">Submit</button>
        <button type="reset">Reset</button>
    </div>
</form>
</body>
</html>
