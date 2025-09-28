package com.t2404e.wellcometojavaweb;

import com.t2404e.wellcometojavaweb.entity.Account;
import com.t2404e.wellcometojavaweb.repository.AccountRepository;
import com.t2404e.wellcometojavaweb.repository.MySQLAccountRepository;

import java.util.List;

public class MainThread {
    public static void main(String[] args) {
        AccountRepository repository = null;
        repository = new MySQLAccountRepository();

        List<Account> accountList = repository.findAll();
        for (Account account : accountList) {
            System.out.println(account);
        }
    }
}

