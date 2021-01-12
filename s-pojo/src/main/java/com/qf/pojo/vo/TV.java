package com.qf.pojo.vo;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "tv")
@Entity
public class TV {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //电视台
    private String television;

    //地区
    private String region;

    //直播链接
    private String url;
}
