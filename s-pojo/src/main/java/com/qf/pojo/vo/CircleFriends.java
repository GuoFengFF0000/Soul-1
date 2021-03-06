package com.qf.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class CircleFriends {

    private Integer cfId;

    private Integer uid;

    private String comment;

    private Integer zanNums;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date commentDate;

    private String commentUser;
}
