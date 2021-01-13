package com.qf.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class RemarkAndUser {

    private Integer rid;

    private String commentDesc;

    private Integer cfId;

    private Integer uid;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date remarkDate;

    private String remarkUser;

    private Integer id;

    private String pic1;
}
