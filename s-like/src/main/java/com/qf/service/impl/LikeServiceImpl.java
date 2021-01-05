package com.qf.service.impl;

import com.qf.pojo.resp.BaseResp;
import com.qf.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired


    @Override
    public BaseResp findRandom(Map map) {
        Integer id = (Integer) map.get("id");

    }
}
