package com.t2404e.wellcometojavaweb;

import com.t2404e.wellcometojavaweb.entity.Account;
import com.t2404e.wellcometojavaweb.repository.MySQLAccountRepository;

import java.util.List;

public class MainThread {
    public static void main(String[] args) {
        MySQLAccountRepository repo = new MySQLAccountRepository();
        Account acc = new Account("Tran Van B", "test123", 1);

        try {
            repo.add(acc);
            System.out.println("Saved account: " + acc);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            System.out.println("Save failed: " + ex.getMessage());
        }

        try {
            List<Account> accounts = repo.findAll();
            for (Account account : accounts) {
                System.out.println(account);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

