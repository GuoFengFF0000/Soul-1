package com.qf.pojo.vo;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "gift")
@Entity
public class Gift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Double price;

    private String pic;
}
