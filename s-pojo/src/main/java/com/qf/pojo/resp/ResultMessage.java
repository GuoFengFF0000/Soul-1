package com.qf.pojo.resp;

import lombok.Data;

@Data
public class ResultMessage {

    private boolean isSystem;

    private String fromEmail;

    private Object message;

}
