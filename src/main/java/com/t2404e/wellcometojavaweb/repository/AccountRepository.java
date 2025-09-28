package com.t2404e.wellcometojavaweb.repository;

import com.t2404e.wellcometojavaweb.entity.Account;
import java.util.List;

public interface AccountRepository {
    Account save(Account account);
    Account update(int id, Account account);
    boolean deleteById(int id);
    Account findById(int id);
    List<Account> findAll();
}
