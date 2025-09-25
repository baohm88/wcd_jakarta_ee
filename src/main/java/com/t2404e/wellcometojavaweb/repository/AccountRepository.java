package com.t2404e.wellcometojavaweb.repository;

import com.t2404e.wellcometojavaweb.entity.Account;

import java.util.List;

public interface AccountRepository {
    Account save(Account account);
    Account update(Account account);

    Account update(Long id, Account account);

    boolean delete(Account account);

    boolean delete(Long id, Account account);

    Account findById(long id);
    List<Account> findAll();
}
