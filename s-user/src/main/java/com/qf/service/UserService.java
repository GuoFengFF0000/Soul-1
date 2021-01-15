package com.qf.service;

import com.qf.pojo.rep.UserRep;
import com.qf.pojo.resp.BaseResp;
import com.qf.pojo.vo.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;



public interface UserService {
    BaseResp login(UserRep userRep);

    BaseResp findAll();

    BaseResp findById(HttpServletRequest request);

    BaseResp registry(UserRep userReq);

    BaseResp editStatus(Integer id);

    List<User> selectAll();

    BaseResp selectById( Integer id);




    BaseResp saveOrUpdate(User user);
    BaseResp gift(Map map);
}
