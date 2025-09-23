<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <!-- Set page metadata and some basic styles -->
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Register</title>
    <style>
        body { font-family: Arial, sans-serif; max-width: 520px; margin: 40px auto; }
        form { display: grid; gap: 12px; }
        label { display: block; font-weight: 600; }
        input[type=text], input[type=password] { width: 100%; padding: 8px; }
        button { padding: 10px 14px; cursor: pointer; }
        .msg {margin: 12px 0; padding: 10px; border-radius: 6px;}
        .msg-err {background: #ffecec; color: #b10000; border: 1px solid #ffb3b3;}
        .msg-ok  { background: #e9fff5; color: #046f43; border: 1px solid #9be0c3; }
        ul.errs {margin: 6px 0 0 18px;}
    </style>
</head>
<body>
<h1>Register</h1>

<%
    // Pull attributes set by the servlet.
    // 'errors' is a List<String>, 'success' is a message, 'formUsername' keeps the typed username.
    java.util.List errors = (java.util.List) request.getAttribute("errors");
    String success = (String) request.getAttribute("success");
    String formUsername = (String) request.getAttribute("formUsername");
    if (formUsername == null) formUsername = "";
%>

<% if (errors != null && !errors.isEmpty()) { %>
<!-- Error block: render all validation messages -->
<div class="msg msg-err">
    <strong>Please double check the following:</strong>
    <ul class="errs">
        <% for (Object e : errors) { %>
        <li><%= e %></li>
        <% } %>
    </ul>
</div>
<% } %>

<% if (success != null) { %>
<!-- Success block -->
<div class="msg msg-ok"><%= success %></div>
<% } %>

<!-- Use contextPath to be safe if the app is not deployed at "/" -->
<form action="<%= request.getContextPath() %>/register" method="post">
    <div>
        <label for="username">Username</label>
        <input type="text" name="username" id="username" value="<%= formUsername %>" autofocus required>
    </div>

    <div>
        <label for="password">Password</label>
        <!-- Pre-fill password after failed validation; do NOT echo passwords -->
        <input type="text" name="password" id="password"  autofocus required>
    </div>
    <div>
        <label for="password">Password</label>
        <input type="password" name="password" id="password" required>
    </div>
    <div>
        <label for="confirmPassword">Confirm Password</label>
        <input type="password" name="confirmPassword" id="confirmPassword" required>
    </div>
    <div>
        <button type="submit">Submit</button>
    </div>
</form>
</body>
</html>
