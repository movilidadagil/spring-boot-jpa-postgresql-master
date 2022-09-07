package com.bezkoder.spring.jpa.postgresql.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class ProductComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "comment_date")
    private String comment_date;

    @Column(name = "product_id")
    private String product_id;

    @Column(name = "user_id")
    private long user_id;


    public ProductComment() {

    }

    public ProductComment(String comment, String comment_date,
                   String product_id, long user_id) {
        this.comment = comment;
        this.comment_date = comment_date;
        this.product_id = product_id;
        this.user_id = user_id;
    }


    @Override
    public String toString() {
        return "Comment [id=" + id + ", product id=" + product_id + ", comment date=" + comment_date+" , user id="+user_id+"]";
    }
}
