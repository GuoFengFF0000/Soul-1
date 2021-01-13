package com.qf.service.impl;

import com.qf.dao.TvRepository;
import com.qf.pojo.resp.BaseResp;
import com.qf.pojo.vo.TV;
import com.qf.service.TVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TVServiceImpl implements TVService {

    @Autowired
    TvRepository tvRepository;

    @Override
    public BaseResp findAll() {

        BaseResp baseResp = new BaseResp();

        List<TV> all = tvRepository.findAll();

        baseResp.setCode(200);
        baseResp.setMessage("查询成功");
        baseResp.setData(all);

        return baseResp;
    }

    @Override
    public BaseResp findByRegion(String region) {

        BaseResp baseResp = new BaseResp();

        List<TV> byRegion = tvRepository.findByRegion(region);

        baseResp.setCode(200);
        baseResp.setMessage("查询成功");
        baseResp.setData(byRegion);

        return baseResp;
    }

    @Override
    public BaseResp findById(Integer id) {

        BaseResp baseResp = new BaseResp();

        Optional<TV> byId = tvRepository.findById(id);

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
    public BaseResp insertOrUpdate(TV tv) {

        BaseResp baseResp = new BaseResp();

        TV tv1 = tvRepository.saveAndFlush(tv);

        if (tv1!=null){
            baseResp.setCode(200);
            baseResp.setMessage("操作成功");
            return baseResp;
        }
        baseResp.setCode(300);
        baseResp.setMessage("操作失败");
        return baseResp;
    }

    @Override
    public BaseResp delete(Integer id) {

        BaseResp baseResp = new BaseResp();

        tvRepository.deleteById(id);

        baseResp.setCode(200);
        baseResp.setMessage("删除成功");
        return baseResp;
    }

    @Override
    public BaseResp findAllByLimit(Integer page, Integer size) {

        BaseResp baseResp = new BaseResp();

        PageRequest pageRequest = new PageRequest((page-1)*size, size);

        Page<TV> all = tvRepository.findAll(pageRequest);
        baseResp.setCode(200);
        baseResp.setMessage("查询成功");
        baseResp.setData(all.getContent());
        baseResp.setCount(all.getTotalElements());
        return baseResp;
    }
}
