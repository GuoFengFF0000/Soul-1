package com.qf.pojo.vo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_name")
    private String userName;
    private String password;
    private String email;
    private String pic1;
    private String pic2;
    private String pic3;
    private Integer age;
    private String sex;
    private String word;
    private String constellation;
    private String message;
}
