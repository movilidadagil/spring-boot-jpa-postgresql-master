package com.bezkoder.spring.jpa.postgresql.repository;

import com.bezkoder.spring.jpa.postgresql.model.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCommentRepository extends JpaRepository<ProductComment, Long> {
  List<ProductComment> findProductCommentsByProduct_id(long product_id);


  List<ProductComment> findProductCommentsByComment_dateBetween(String start_date, String end_date);

  List<ProductComment> findProductCommentsByUser_id(long user_id);

  List<ProductComment> findProductCommentsByUser_idAndComment_dateBetween(String start_date, String end_date, String user_id);


}
