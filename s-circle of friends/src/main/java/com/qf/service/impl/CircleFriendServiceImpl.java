package com.qf.service.impl;

import com.qf.dao.CircleFriendMapper;
import com.qf.pojo.CircleFriends;
import com.qf.pojo.resp.BaseResp;
import com.qf.service.CircleFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CircleFriendServiceImpl implements CircleFriendService {


    @Autowired
    CircleFriendMapper  circleFriendMapper;

    @Override
    public BaseResp insertComment(CircleFriends circleFriends) {
        BaseResp baseResp = new BaseResp();
        if (circleFriends.getComment().equals("")&&circleFriends.getComment()!=null){
            baseResp.setCode(201);
            baseResp.setMessage("内容不能为空!");
            return baseResp;
        }
        int friend = circleFriendMapper.insertComment(circleFriends);
        baseResp.setCode(200);
        baseResp.setData(friend);
        baseResp.setMessage("发布成功");
        return baseResp;
    }

    @Override
    public BaseResp circleFriendFindAllByNew() {

        BaseResp baseResp = new BaseResp();
        List list = circleFriendMapper.circleFriendFindAllByNew();
        baseResp.setCode(200);
        baseResp.setMessage("查询成功");
        baseResp.setData(list);
        return baseResp;
    }

    @Override
    public BaseResp circleFriendFindAllByNewByZanNums() {
        BaseResp baseResp = new BaseResp();
        List list = circleFriendMapper.circleFriendFindAllByNewByZanNums();
        baseResp.setCode(200);
        baseResp.setMessage("查询成功");
        baseResp.setData(list);
        return baseResp;
    }

    @Override
    public BaseResp addZanNums(Integer cfId) {
        BaseResp baseResp = new BaseResp();
        int i = circleFriendMapper.addZanNums(cfId);
        baseResp.setData(i);
        baseResp.setCode(200);
        baseResp.setMessage("点赞成功!");
        return baseResp;


    }
    @Override
    public BaseResp adminFriendAdd(CircleFriends circleFriends) {
        BaseResp baseResp = new BaseResp();
        int i = circleFriendMapper.adminFriendAdd(circleFriends);
        baseResp.setCode(200);
        baseResp.setMessage("增加成功");
        baseResp.setData(i);
        return baseResp;
    }

    @Override
    public BaseResp adminFriendDel(Integer cfId) {
        BaseResp baseResp = new BaseResp();
        int i = circleFriendMapper.adminFriendDel(cfId);
        baseResp.setCode(200);
        baseResp.setMessage("删除成功");
        baseResp.setData(i);
        return baseResp;
    }

    @Override
    public BaseResp adminFriendUpdate(CircleFriends circleFriends) {

        BaseResp baseResp = new BaseResp();
        if (circleFriends!=null){
            int i = circleFriendMapper.adminFriendUpdate(circleFriends);
            baseResp.setCode(200);
            baseResp.setMessage("修改成功");
            baseResp.setData(circleFriends);
            return baseResp;
        }
        baseResp.setCode(201);
        baseResp.setMessage("修改失败");
        return  baseResp;
    }

    @Override
    public BaseResp adminFriendFindAll() {
        BaseResp baseResp = new BaseResp();
        List list = circleFriendMapper.adminFriendFindAll();
        baseResp.setData(list);
        baseResp.setCode(200);
        baseResp.setMessage("查询成功");
        return baseResp;
    }

    @Override
    public BaseResp adminFriendFindByCfId(Integer cfId) {
        BaseResp baseResp = new BaseResp();
        CircleFriends circleFriends = circleFriendMapper.adminFriendFindByCfId(cfId);
        if(circleFriends != null){
            baseResp.setData(circleFriends);
            baseResp.setCode(200);
            baseResp.setMessage("查询成功");
            return baseResp;
        }
        baseResp.setCode(201);
        baseResp.setMessage("查询失败");
        return baseResp;

    }


}
