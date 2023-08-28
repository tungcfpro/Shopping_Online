package com.poly.dao;


import com.poly.entity.Product;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer>{


    @Query("select p from Product p where p.category.id=?1")
    List<Product> findByCategoryId(String cid, Pageable pageable);
    @Query("SELECT p FROM Product p WHERE p.category.id = ?1 AND p.price >= ?2 AND p.price <= ?3")
    List<Product> findByPriceAndCategoryId(String categoryId, Double minPrice, Double maxPrice);
    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%")
    List<Product> searchByKeyword(String keyword, Pageable pageable);
    List<Product> findByPriceBetween(double minPrice, double maxPrice, Pageable pageable);

}
