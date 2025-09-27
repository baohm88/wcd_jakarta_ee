package com.t2404e.wellcometojavaweb.repository;

import com.t2404e.wellcometojavaweb.entity.Account;
import java.util.List;

public interface AccountRepository {
    void add(Account account);
    List<Account> findAll();
    Account findById(long id);
    void update(long id, Account account);
    void delete(long id);
}
