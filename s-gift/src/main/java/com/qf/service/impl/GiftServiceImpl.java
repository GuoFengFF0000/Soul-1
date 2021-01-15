package com.qf.service.impl;

import com.qf.dao.GiftRepository;
import com.qf.pojo.resp.BaseResp;
import com.qf.pojo.vo.Gift;
import com.qf.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GiftServiceImpl implements GiftService {

    @Autowired
    GiftRepository giftRepository;

    @Override
    public BaseResp findAll() {

        BaseResp baseResp = new BaseResp();

        List<Gift> all = giftRepository.findAll();

        baseResp.setData(all);
        baseResp.setMessage("查询成功");
        baseResp.setCode(200);
        return baseResp;
    }

    @Override
    public BaseResp findById(Integer id) {

        BaseResp baseResp = new BaseResp();

        Optional<Gift> byId = giftRepository.findById(id);

        if (byId.isPresent()){
            Gift gift = byId.get();
            baseResp.setCode(200);
            baseResp.setMessage("查询成功");
            baseResp.setData(gift);
            return baseResp;
        }
        baseResp.setCode(300);
        baseResp.setMessage("查询失败");
        return baseResp;
    }
}
