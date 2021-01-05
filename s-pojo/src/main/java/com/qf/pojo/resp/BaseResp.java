package com.qf.pojo.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * 封装返回类型
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResp {

    private Integer code;

    private String message;

    private Object data;

    private Long count;

}
