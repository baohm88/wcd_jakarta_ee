<%@ page import="java.util.List" %>
<%@ page import="com.t2404e.wellcometojavaweb.entity.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Product> products = (List<Product>) request.getAttribute("products");
    String keyword = request.getParameter("keyword") == null ? "" : request.getParameter("keyword");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý sản phẩm</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <style>
        th.sortable { cursor: pointer; }
        th.sortable .arrow { font-size: 12px; margin-left: 4px; }
    </style>
</head>
<body class="container mt-4">

<h2>Danh sách sản phẩm</h2>

<!-- Tìm kiếm -->
<form method="get" class="mb-3">
    <div class="input-group">
        <input type="text" name="keyword" class="form-control"
               placeholder="Tìm theo tên hoặc mô tả..." value="<%= keyword %>">
        <button class="btn btn-primary">Tìm</button>
    </div>
</form>

<!-- Bảng sản phẩm -->
<table class="table table-bordered table-striped">
    <thead>
    <tr>
        <th class="sortable" onclick="sortTable('id')">ID <span class="arrow" id="arrow-id">⇅</span></th>
        <th class="sortable" onclick="sortTable('name')">Tên <span class="arrow" id="arrow-name">⇅</span></th>
        <th>Mô tả</th>
        <th class="sortable" onclick="sortTable('price')">Giá <span class="arrow" id="arrow-price">⇅</span></th>
        <th class="sortable" onclick="sortTable('status')">Trạng thái <span class="arrow" id="arrow-status">⇅</span></th>
        <th>Hành động</th>
    </tr>
    </thead>
    <tbody>
    <% if (products != null && !products.isEmpty()) {
        for (Product p : products) { %>
    <tr>
        <td><%= p.getId() %></td>
        <td><%= p.getName() %></td>
        <td><%= p.getDescription() %></td>
        <td><%= String.format("%,.0f", p.getPrice()) %> VND</td>
        <td><%= p.getStatus() %></td>
        <td>
            <!-- Nút Edit -->
            <a href="<%= request.getContextPath() %>/admin/product/edit?id=<%= p.getId() %>"
               class="btn btn-sm btn-warning">Edit</a>

            <!-- Nút Delete (mở modal) -->
            <button class="btn btn-sm btn-danger" onclick="confirmDelete(<%= p.getId() %>)">Delete</button>
        </td>
    </tr>
    <%  } } else { %>
    <tr><td colspan="6" class="text-center">Không có sản phẩm nào.</td></tr>
    <% } %>
    </tbody>
</table>

<!-- Modal xác nhận xóa -->
<div class="modal fade" id="deleteModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Xác nhận xóa</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                Bạn có chắc chắn muốn xóa sản phẩm này?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                <a id="deleteConfirmBtn" class="btn btn-danger">Xóa</a>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    // Hàm confirm delete
    function confirmDelete(id) {
        const modal = new bootstrap.Modal(document.getElementById('deleteModal'));
        // Nếu bạn chọn Cách A (annotation = /admin/product/delete):
        document.getElementById('deleteConfirmBtn').href =
            '<%= request.getContextPath() %>/admin/product/delete?id=' + id;
        modal.show();
    }

    // Hàm sort
    let currentSort = {};
    function sortTable(field) {
        const direction = currentSort[field] === 'asc' ? 'desc' : 'asc';
        window.location.href =
            '<%= request.getContextPath() %>/admin/product/list?sort=' + field +
            '&order=' + direction + '&keyword=<%= keyword %>';
    }
</script>

</body>
</html>