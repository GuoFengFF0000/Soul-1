package com.qf.service;

import com.qf.pojo.rep.UserRep;
import com.qf.pojo.resp.BaseResp;

public interface UserService {
    BaseResp login(UserRep userRep);

    BaseResp findAll();

    BaseResp findById(Integer id);
}
