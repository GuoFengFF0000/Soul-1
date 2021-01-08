package com.qf.service;

import com.qf.pojo.rep.UserRep;
import com.qf.pojo.resp.BaseResp;
import com.qf.pojo.vo.User;

import java.util.List;


public interface UserService {
    BaseResp login(UserRep userRep);

    BaseResp findAll();

    BaseResp findById(Integer id);

    BaseResp registry(UserRep userReq);

    BaseResp editStatus(Integer id);

    List<User> selectAll();

    BaseResp selectById( Integer id);
}
