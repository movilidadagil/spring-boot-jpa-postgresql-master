package com.bezkoder.spring.jpa.postgresql.repository;

import com.bezkoder.spring.jpa.postgresql.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
  List<Product> findAllByExpire_date();

  List<Product> findProductByExpire_date(String product_name);

  Optional<Product> findProductByProductname(String product_name);
}
