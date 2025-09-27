package com.t2404e.wellcometojavaweb.repository;

import com.t2404e.wellcometojavaweb.entity.Account;
import com.t2404e.wellcometojavaweb.helper.MysqlConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MySQLAccountRepository implements AccountRepository {

    @Override
    public void add(Account account) {
        try {
            Connection con = MysqlConnector.getConnection();
            String sql = "INSERT INTO account(username, passwordHash, status) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPasswordHash());
            ps.setInt(3, account.getStatus());
            ps.executeUpdate();
            System.out.println("Thêm account thành công!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Account> findAll() {
        List<Account> list = new ArrayList<>();
        try {
            Connection con = MysqlConnector.getConnection();
            String sql = "SELECT * FROM account";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account acc = new Account(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("passwordHash"),
                        rs.getInt("status")
                );
                list.add(acc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Account findById(long id) {
        try {
            Connection con = MysqlConnector.getConnection();
            String sql = "SELECT * FROM account WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Account(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("passwordHash"),
                        rs.getInt("status")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(long id, Account account) {
        try {
            Connection con = MysqlConnector.getConnection();
            String sql = "UPDATE account SET username=?, passwordHash=?, status=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPasswordHash());
            ps.setInt(3, account.getStatus());
            ps.setLong(4, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Cập nhật thành công!");
            } else {
                System.out.println("Không tìm thấy account để cập nhật.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        try {
            Connection con = MysqlConnector.getConnection();
            String sql = "DELETE FROM account WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Xóa thành công!");
            } else {
                System.out.println("Không tìm thấy account để xóa.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
