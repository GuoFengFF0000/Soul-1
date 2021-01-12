package com.qf.service.impl;

import com.qf.dao.AnchorRepository;
import com.qf.pojo.resp.BaseResp;
import com.qf.pojo.vo.Anchor;
import com.qf.service.AnchorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnchorServiceImpl implements AnchorService {

    @Autowired
    AnchorRepository anchorRepository;

    @Override
    public BaseResp findAll() {

        BaseResp baseResp = new BaseResp();

        List<Anchor> all = anchorRepository.findAll();

        baseResp.setCode(200);
        baseResp.setMessage("查询成功");
        baseResp.setData(all);
        return baseResp;
    }

    @Override
    public BaseResp findById(Integer id) {

        BaseResp baseResp = new BaseResp();

        Optional<Anchor> byId = anchorRepository.findById(id);

        if (byId.isPresent()){
            baseResp.setCode(200);
            baseResp.setMessage("查询成功");
            baseResp.setData(byId.get());
            return baseResp;
        }
        baseResp.setCode(300);
        baseResp.setMessage("查询失败");
        return baseResp;
    }

    @Override
    public BaseResp insertOrUpdate(Anchor anchor) {

        BaseResp baseResp = new BaseResp();

        Anchor anchor1 = anchorRepository.saveAndFlush(anchor);

        if (anchor1!=null){
            baseResp.setData(anchor1);
            baseResp.setMessage("操作成功");
            baseResp.setCode(200);
            return baseResp;
        }
        baseResp.setMessage("操作失败");
        baseResp.setCode(300);
        return baseResp;
    }
}
