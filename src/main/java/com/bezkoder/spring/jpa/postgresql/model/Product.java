package com.bezkoder.spring.jpa.postgresql.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String productname;

    @Column(name = "price")
    private String productprice;

    @Column(name = "expire_date")
    private String expire_date;


    public Product() {

    }

    public Product(String productname, String productprice,
                String expire_date) {
        this.productname = productname;
        this.productprice = productprice;
        this.expire_date = expire_date;
    }


    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + productname + ", price=" + productprice +  ", expire date=" + expire_date+"]";
    }
}
