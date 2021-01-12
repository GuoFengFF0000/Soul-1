package com.qf.service;

import com.qf.pojo.resp.BaseResp;
import com.qf.pojo.vo.Anchor;

public interface AnchorService {
    BaseResp findAll();

    BaseResp findById(Integer id);

    BaseResp insertOrUpdate(Anchor anchor);
}
