package com.qf.service;

import com.qf.pojo.resp.BaseResp;

public interface GiftService {
    BaseResp findAll();

    BaseResp findById(Integer id);
}
