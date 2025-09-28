//package com.t2404e.wellcometojavaweb.repository;
//
//import com.t2404e.wellcometojavaweb.entity.Product;
//import com.t2404e.wellcometojavaweb.helper.MySqlHelper;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//
//public class MySQLProductRepository implements ProductRepository {
//
//    private static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    private static final String TABLE_NAME = "products";
//
//    @Override
//    public Product save(Product product) {
//        String sql = "INSERT INTO " + TABLE_NAME + " (id, name, description, content, thumbnail, price, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
//
//        try (Connection connection = MySqlHelper.getConnection()) {
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setLong(1, product.getId());
//            preparedStatement.setString(2, product.getName());
//            preparedStatement.setString(3, product.getDescription());
//            preparedStatement.setString(4, product.getContent());
//            preparedStatement.setString(5, product.getThumbnail());
//            preparedStatement.setDouble(6, product.getPrice());
//            preparedStatement.setInt(7, product.getStatus().getCode());
//
//            int rowsAffected = preparedStatement.executeUpdate();
//            if (rowsAffected > 0) {
//                System.out.println("Insert thành công");
//            } else {
//                System.err.println("Insert thất bại");
//            }
//
//        } catch (SQLException e) {
//            System.err.println("Lỗi khi insert account: " + e.getMessage());
//            e.printStackTrace();
//        }
//
//        return product;
//    }
//
//    @Override
//    public Product update(long id, Product account) {
//        return null;
//    }
//
//    @Override
//    public boolean deleteById(long id) {
//        return false;
//    }
//
//    @Override
//    public Product findById(long id) {
//        return null;
//    }
//
//    @Override
//    public List<Product> findAll(String keyword, String sort, String order) {
//        List<Product> products = new ArrayList<>();
//        StringBuilder sql = new StringBuilder("SELECT * FROM products WHERE 1=1");
//
//        if (keyword != null && !keyword.isEmpty()) {
//            sql.append(" AND (name LIKE ? OR description LIKE ?)");
//        }
//
//        if (sort != null && !sort.isEmpty()) {
//            sql.append(" ORDER BY ").append(sort);
//            if ("desc".equalsIgnoreCase(order)) {
//                sql.append(" DESC");
//            } else {
//                sql.append(" ASC");
//            }
//        }
//
//        try (Connection connection = MySqlHelper.getConnection();
//             PreparedStatement ps = connection.prepareStatement(sql.toString())) {
//
//            if (keyword != null && !keyword.isEmpty()) {
//                ps.setString(1, "%" + keyword + "%");
//                ps.setString(2, "%" + keyword + "%");
//            }
//
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                Product p = new Product();
//                p.setId(rs.getLong("id"));
//                p.setName(rs.getString("name"));
//                p.setDescription(rs.getString("description"));
//                p.setPrice(rs.getDouble("price"));
//                // chuyển status int → enum nếu cần
//                products.add(p);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return products;
//    }
//}


package com.t2404e.wellcometojavaweb.repository;

import com.t2404e.wellcometojavaweb.entity.Product;
import com.t2404e.wellcometojavaweb.entity.helper.ProductEnum;
import com.t2404e.wellcometojavaweb.helper.MySqlHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MySQLProductRepository implements ProductRepository {

    private static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String TABLE_NAME = "products";

    @Override
    public Product save(Product product) {
        String sql = "INSERT INTO " + TABLE_NAME + " (id, name, description, content, thumbnail, price, status, createdAt) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = MySqlHelper.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setString(4, product.getContent());
            preparedStatement.setString(5, product.getThumbnail());
            preparedStatement.setDouble(6, product.getPrice());
            preparedStatement.setInt(7, product.getStatus().getCode());
            preparedStatement.setString(8, TIMESTAMP_FORMAT.format(Calendar.getInstance().getTime()));

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Insert thành công");
            } else {
                System.err.println("Insert thất bại");
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi insert product: " + e.getMessage());
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public Product update(long id, Product product) {
        String sql = "UPDATE " + TABLE_NAME +
                " SET name=?, description=?, content=?, thumbnail=?, price=?, status=?, updatedAt=? WHERE id=?";

        try (Connection connection = MySqlHelper.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setString(3, product.getContent());
            ps.setString(4, product.getThumbnail());
            ps.setDouble(5, product.getPrice());
            ps.setInt(6, product.getStatus().getCode());
            ps.setString(7, TIMESTAMP_FORMAT.format(Calendar.getInstance().getTime()));
            ps.setLong(8, id);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Update thành công");
                return product;
            } else {
                System.out.println("Không tìm thấy sản phẩm với id=" + id);
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi update product: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteById(long id) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE id=?";

        try (Connection connection = MySqlHelper.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Xóa thành công sản phẩm id=" + id);
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa product: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public Product findById(long id) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id=?";
        try (Connection connection = MySqlHelper.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Product p = new Product();
                    p.setId(rs.getLong("id"));
                    p.setName(rs.getString("name"));
                    p.setDescription(rs.getString("description"));
                    p.setContent(rs.getString("content"));
                    p.setThumbnail(rs.getString("thumbnail"));
                    p.setPrice(rs.getDouble("price"));
                    p.setStatus(ProductEnum.fromCode(rs.getInt("status")));
                    return p;
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm product: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Product> findAll(String keyword, String sort, String order) {
        List<Product> products = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM " + TABLE_NAME + " WHERE 1=1");

        if (keyword != null && !keyword.isEmpty()) {
            sql.append(" AND (name LIKE ? OR description LIKE ?)");
        }

        if (sort != null && !sort.isEmpty()) {
            sql.append(" ORDER BY ").append(sort);
            if ("desc".equalsIgnoreCase(order)) {
                sql.append(" DESC");
            } else {
                sql.append(" ASC");
            }
        }

        try (Connection connection = MySqlHelper.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql.toString());

            if (keyword != null && !keyword.isEmpty()) {
                ps.setString(1, "%" + keyword + "%");
                ps.setString(2, "%" + keyword + "%");
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getLong("id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getDouble("price"));
                p.setThumbnail(rs.getString("thumbnail"));
                p.setContent(rs.getString("content"));
                int statusCode = rs.getInt("status");
                p.setStatus(ProductEnum.fromCode(statusCode));

                products.add(p);
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi lấy danh sách products: " + e.getMessage());
            e.printStackTrace();
        }
        return products;
    }
}
