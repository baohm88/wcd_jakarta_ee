package com.t2404e.wellcometojavaweb.repository;

import com.t2404e.wellcometojavaweb.entity.Account;
import com.t2404e.wellcometojavaweb.helper.MysqlConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.sql.ResultSet;


public class MySQLAccountRepository implements AccountRepository {
    @Override
    public Account save(Account account) {
        final String sql = "INSERT INTO account (username, passwordHash, status) VALUES (?, ?, ?)";
        

        try (Connection con = MysqlConnector.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPasswordHash());
            ps.setInt(3, account.getStatus());

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new RuntimeException("Insert account failed: no row affected");
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    account.setId(rs.getLong(1));
                }
            }
            return account;

        } catch (java.sql.SQLIntegrityConstraintViolationException e) {
            throw new RuntimeException("Username already exists: " + account.getUsername(), e);
        } catch (Exception e) {
            throw new RuntimeException("Insert account failed", e);
        }
    }


    @Override
    public Account update(Long id, Account account) {
        return null;
    }

    @Override
    public boolean delete(Long id, Account account) {
        return false;
    }

    @Override
    public Account findById(long id) {
        return null;
    }

    @Override
    public List<Account> findAll() {
        String sql = "SELECT * FROM account ORDER BY id";

        List<Account> results = new java.util.ArrayList<>();

        try (
             Connection con = MysqlConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Account acc = new Account();
                acc.setId(rs.getLong("id"));
                acc.setUsername(rs.getString("username"));
                acc.setStatus(rs.getInt("status"));
                results.add(acc);
            }
            return results;

        } catch (Exception e) {
            throw new RuntimeException("Query findAll() failed", e);
        }
    }
}
