package com.qf.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class FollowFriends {

    private Integer fid;

    private Integer uid;

    private String followUser;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date commentDate;

    private String commentUser;

    private Integer zanNums;

    private Integer cfId;

    private Integer uId;

    private String comment;

    private String pic1;

    private String message;
}
