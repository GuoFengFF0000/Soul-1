package com.qf.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(name = "anchor")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Anchor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String pic;

    private Integer fans;

    private Integer level;

    private Integer age;

    private String constellation;

    private String area;

    private Integer distance;

    private String tag;

    private Integer fire;

    private String url;

    private Integer number;

    //余额
    private Double balance;
}
