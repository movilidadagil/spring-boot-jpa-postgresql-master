package com.bezkoder.spring.jpa.postgresql.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "telephone")
    private String telephone;

    public User() {

    }

    public User(String name, String surname,
                String email, String telephone) {
        this.name = name;
        this.surname = surname;
        this.telephone = telephone;
        this.email = email;
    }


    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", surname=" + surname +  ", telephone=" + telephone+", email=" + email + "]";
    }
}
