package com.qf.pojo.rep;

import lombok.Data;

@Data
public class UserRep {
    private Integer id;
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
