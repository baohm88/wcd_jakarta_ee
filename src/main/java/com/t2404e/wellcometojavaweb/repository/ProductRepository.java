package com.t2404e.wellcometojavaweb.repository;

import com.t2404e.wellcometojavaweb.entity.Product;

import java.util.List;

public interface ProductRepository {
    Product save(Product account);
    Product update(long id, Product account);
    boolean deleteById(long id);
    Product findById(long id);
    List<Product> findAll(String keyword, String sort, String order);
}
