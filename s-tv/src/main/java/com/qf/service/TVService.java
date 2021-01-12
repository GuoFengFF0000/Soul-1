package com.qf.service;

import com.qf.pojo.resp.BaseResp;
import com.qf.pojo.vo.TV;

public interface TVService {
    BaseResp findAll();

    BaseResp findByRegion(String region);

    BaseResp findById(Integer id);

    BaseResp insertOrUpdate(TV tv);

    BaseResp delete(Integer id);

    BaseResp findAllByLimit(Integer page, Integer size);
}
