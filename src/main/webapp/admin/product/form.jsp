<%--
  Created by IntelliJ IDEA.
  User: baoha
  Date: 9/28/25
  Time: 8:58 PM
  To change this template use File | Settings | File Templates.
--%>

<%--
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Thêm sản phẩm</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- CKEditor -->
    <script src="https://cdn.ckeditor.com/ckeditor5/41.1.0/classic/ckeditor.js"></script>

    <style>
        body {
            background-color: #f8f9fa;
            font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
        }

        .form-container {
            max-width: 700px;
            margin: 40px auto;
            background: white;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 0 20px rgba(0,0,0,0.05);
        }

        .ck-editor__editable[role="textbox"] {
            min-height: 200px;
        }

        #image-preview {
            margin-top: 10px;
            max-width: 100%;
            max-height: 250px;
            display: none;
            border-radius: 8px;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="form-container">
        <h2 class="text-center">Thêm sản phẩm mới</h2>
        <form action="/admin/product/create" method="post">
            <div class="mb-3">
                <label for="name" class="form-label">Tên sản phẩm</label>
                <input type="text" class="form-control" id="name" name="name" required />
            </div>

            <div class="mb-3">
                <label for="description" class="form-label">Mô tả ngắn</label>
                <textarea class="form-control" id="description" name="description" rows="3"></textarea>
            </div>

            <div class="mb-3">
                <label for="content" class="form-label">Nội dung</label>
                <textarea id="content" name="content"></textarea>
            </div>

            <div class="mb-3">
                <label for="price" class="form-label">Giá</label>
                <input type="number" class="form-control" id="price" name="price" min="0" step="0.01" required />
            </div>

            <div class="mb-3">
                <label for="image" class="form-label">Ảnh sản phẩm</label>
                <input type="hidden" name="thumbnail" id="product-thumbnail">
                <img src="" alt="" id="image-preview">
                <button type="button" id="upload_widget" class="cloudinary-button">Upload files</button>
            </div>

            <div class="text-end">
                <button type="submit" class="btn btn-primary">Lưu</button>
            </div>
        </form>
    </div>
</div>

<!-- CKEditor init -->
<script>
    ClassicEditor
        .create(document.querySelector('#content'))
        .catch(error => {
            console.error(error);
        });
</script>


<script src="https://upload-widget.cloudinary.com/latest/global/all.js" type="text/javascript"></script>

<script type="text/javascript">
    var myWidget = cloudinary.createUploadWidget({
            cloudName: 'xuanhung2401',
            uploadPreset: 'ufx1h3qp'}, (error, result) => {
            if (!error && result && result.event === "success") {
                console.log('Done! Here is the image info: ', result.info.secure_url);
                document.getElementById("product-thumbnail").value = result.info.secure_url;
                document.getElementById("image-preview").src = result.info.secure_url;
                document.getElementById("image-preview").style.display = "block";
            }
        }
    )

    document.getElementById("upload_widget").addEventListener("click", function(){
        myWidget.open();
    }, false);
</script>

</body>
</html>

--%>

<%@ page import="com.t2404e.wellcometojavaweb.entity.Product" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    Product product = (Product) request.getAttribute("product");
    boolean editing = (product != null);
%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title><%= editing ? "Cập nhật sản phẩm" : "Thêm sản phẩm mới" %></title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- CKEditor -->
    <script src="https://cdn.ckeditor.com/ckeditor5/41.1.0/classic/ckeditor.js"></script>
</head>
<body>

<div class="container">
    <div class="form-container">
        <h2 class="text-center"><%= editing ? "Cập nhật sản phẩm" : "Thêm sản phẩm mới" %></h2>

        <form action="<%= editing ? "/admin/product/update" : "/admin/product/create" %>" method="post">
            <% if (editing) { %>
            <input type="hidden" name="id" value="<%= product.getId() %>">
            <% } %>

            <div class="mb-3">
                <label for="name" class="form-label">Tên sản phẩm</label>
                <input type="text" class="form-control" id="name" name="name"
                       value="<%= editing ? product.getName() : "" %>" required />
            </div>

            <div class="mb-3">
                <label for="description" class="form-label">Mô tả ngắn</label>
                <textarea class="form-control" id="description" name="description" rows="3"><%= editing ? product.getDescription() : "" %></textarea>
            </div>

            <div class="mb-3">
                <label for="content" class="form-label">Nội dung</label>
                <textarea id="content" name="content"><%= editing ? product.getContent() : "" %></textarea>
            </div>

            <div class="mb-3">
                <label for="price" class="form-label">Giá</label>
                <input type="number" class="form-control" id="price" name="price" min="0" step="0.01"
                       value="<%= editing ? product.getPrice() : "" %>" required />
            </div>

            <div class="mb-3">
                <label for="image" class="form-label">Ảnh sản phẩm</label>
                <input type="hidden" name="thumbnail" id="product-thumbnail"
                       value="<%= editing ? product.getThumbnail() : "" %>">
                <img src="<%= editing ? product.getThumbnail() : "" %>"
                     alt="" id="image-preview"
                     style="<%= editing && product.getThumbnail()!=null ? "display:block;max-width:100%;max-height:250px;" : "display:none;" %>">
                <button type="button" id="upload_widget" class="cloudinary-button">Upload files</button>
            </div>

            <div class="text-end">
                <button type="submit" class="btn btn-primary"><%= editing ? "Cập nhật" : "Lưu" %></button>
                <a href="<%= request.getContextPath() %>/admin/product/list" class="btn btn-secondary">Quay lại</a>
            </div>
        </form>
    </div>
</div>

<!-- CKEditor init -->
<script>
    ClassicEditor
        .create(document.querySelector('#content'))
        .catch(error => console.error(error));
</script>

<!-- Cloudinary Upload -->
<script src="https://upload-widget.cloudinary.com/latest/global/all.js" type="text/javascript"></script>
<script type="text/javascript">
    var myWidget = cloudinary.createUploadWidget({
        cloudName: 'xuanhung2401',
        uploadPreset: 'ufx1h3qp'
    }, (error, result) => {
        if (!error && result && result.event === "success") {
            document.getElementById("product-thumbnail").value = result.info.secure_url;
            document.getElementById("image-preview").src = result.info.secure_url;
            document.getElementById("image-preview").style.display = "block";
        }
    });

    document.getElementById("upload_widget").addEventListener("click", function(){
        myWidget.open();
    }, false);
</script>

</body>
</html>
