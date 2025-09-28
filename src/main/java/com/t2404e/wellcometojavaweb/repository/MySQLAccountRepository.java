package com.t2404e.wellcometojavaweb.repository;

import com.t2404e.wellcometojavaweb.entity.Account;
import com.t2404e.wellcometojavaweb.helper.MySqlHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MySQLAccountRepository implements AccountRepository {

    private static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Account save(Account account) {
        String sql = "INSERT INTO accounts (username, email, passwordHash, status) VALUES (?, ?, ?, ?)";

        try (Connection connection = MySqlHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getEmail());
            preparedStatement.setString(3, account.getPasswordHash());
            preparedStatement.setInt(4, account.getStatus());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Insert thành công");
            } else {
                System.err.println("Insert thất bại");
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi insert account: " + e.getMessage());
            e.printStackTrace();
        }

        return account;
    }

    @Override
    public Account update(int id, Account account) {
        String sql = "UPDATE accounts SET username = ?, email = ?, passwordHash = ?, updatedAt = ?, updatedBy = ?, status = ? WHERE id = ?";

        try (Connection connection = MySqlHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getEmail());
            preparedStatement.setString(3, account.getPasswordHash());

            String currentTime = TIMESTAMP_FORMAT.format(Calendar.getInstance().getTime());
            preparedStatement.setString(4, currentTime);
            preparedStatement.setInt(5, -1); // hardcoded: người cập nhật

            preparedStatement.setInt(6, account.getStatus());
            preparedStatement.setInt(7, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Update thành công");
            } else {
                System.err.println("Không tìm thấy account để update với id: " + id);
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi update account: " + e.getMessage());
            e.printStackTrace();
        }

        return account;
    }

    @Override
    public boolean deleteById(int id) {
        String sql = "DELETE FROM accounts WHERE id = ?";

        try (Connection connection = MySqlHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa account với id: " + id);
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Account findById(int id) {
        String sql = "SELECT * FROM accounts WHERE id = ?";
        Account account = null;

        try (Connection connection = MySqlHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    account = new Account();
                    account.setId(rs.getInt("id"));
                    account.setUsername(rs.getString("username"));
                    account.setEmail(rs.getString("email"));
                    account.setStatus(rs.getInt("status"));
                }
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm account theo id: " + id);
            e.printStackTrace();
        }

        return account;
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM accounts";

        try (Connection connection = MySqlHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                Account account = new Account();
                account.setId(rs.getInt("id"));
                account.setUsername(rs.getString("username"));
                account.setEmail(rs.getString("email"));
                account.setStatus(rs.getInt("status"));
                accounts.add(account);
            }

            System.out.println("Lấy danh sách account thành công");

        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách account");
            e.printStackTrace();
        }

        return accounts;
    }
}

