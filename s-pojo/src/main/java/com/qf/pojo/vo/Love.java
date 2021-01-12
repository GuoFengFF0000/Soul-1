package com.qf.pojo.vo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "love")
public class Love {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private Integer likeId;

    private String sta;

}
