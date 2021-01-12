package com.qf.service;

import com.qf.pojo.rep.UserRep;
import com.qf.pojo.resp.BaseResp;
import com.qf.pojo.vo.User;

import java.util.Map;


public interface UserService {
    BaseResp login(UserRep userRep);

    BaseResp findAll();

    BaseResp findById(Integer id);

    BaseResp registry(UserRep userReq);

    BaseResp editStatus(Integer id);

    BaseResp selectAll();

    BaseResp selectById( Integer id);

    BaseResp gift(Map map);
}
