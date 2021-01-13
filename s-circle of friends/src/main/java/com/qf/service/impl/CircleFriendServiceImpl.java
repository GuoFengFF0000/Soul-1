package com.qf.service.impl;

import com.qf.client.UserClient;
import com.qf.dao.CircleFriendMapper;
import com.qf.pojo.vo.CircleFriends;
import com.qf.pojo.resp.BaseResp;
import com.qf.pojo.vo.Follow;
import com.qf.pojo.vo.Remark;
import com.qf.service.CircleFriendService;
import com.qf.utils.CookieUtils;
import com.qf.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class CircleFriendServiceImpl implements CircleFriendService {


    @Autowired
    CircleFriendMapper  circleFriendMapper;

    @Autowired
    UserClient userClient;

    @Autowired
    CookieUtils cookieUtils;

    @Autowired
    JWTUtils jwtUtils;

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
    public BaseResp addZanNums(Integer cfId,HttpServletRequest request) {
        BaseResp baseResp = new BaseResp();
        Cookie[] cookies = request.getCookies();
        String token = cookieUtils.getToken(cookies);
        if (token == null){
            baseResp.setCode(201);
            baseResp.setMessage("请登录");
            return baseResp;
        }
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

    @Override
    public BaseResp findById(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String token = cookieUtils.getToken(cookies);
        Map verify = jwtUtils.Verify(token);
        BaseResp baseResp = userClient.findById(verify);
        return  baseResp;

    }

    @Override
    public BaseResp insertRemark(Remark remark,HttpServletRequest request) {
        BaseResp baseResp = new BaseResp();
        Cookie[] cookies = request.getCookies();
        String token = cookieUtils.getToken(cookies);
        if (token == null){
            baseResp.setCode(201);
            baseResp.setMessage("请登录后评论");
            return baseResp;
        }
        int i = circleFriendMapper.insertRemark(remark);
        baseResp.setCode(200);
        baseResp.setMessage("评论成功");
        baseResp.setData(i);
        return baseResp;
    }

    @Override
    public BaseResp findAllRemarkByCfId(Integer cfId) {
        BaseResp baseResp = new BaseResp();
        List allRemarkByCfId = circleFriendMapper.findAllRemarkByCfId(cfId);
        baseResp.setCode(200);
        baseResp.setData(allRemarkByCfId);
        baseResp.setMessage("查找成功");
        return baseResp;

    }

    @Override
    public BaseResp addFollow(Follow follow) {
        BaseResp baseResp = new BaseResp();
        int i = circleFriendMapper.addFollow(follow);
        baseResp.setCode(200);
        baseResp.setData(i);
        baseResp.setMessage("关注成功");
        return baseResp;
    }

    @Override
    public BaseResp findFollowByUid(Integer uid) {
        BaseResp baseResp = new BaseResp();
        int count = circleFriendMapper.findFollowByUid(uid);
        baseResp.setCode(200);
        baseResp.setData(count);
        baseResp.setMessage("查询成功");
        return baseResp;
    }

    @Override
    public BaseResp findFollowByFollowUser(String followUser) {
        BaseResp baseResp = new BaseResp();
        int count = circleFriendMapper.findFollowByFollowUser(followUser);
        baseResp.setCode(200);
        baseResp.setData(count);
        baseResp.setMessage("查询成功");
        return baseResp;
    }

    @Override
    public BaseResp findFollowFriends(Integer id) {
        BaseResp baseResp = new BaseResp();
        List followFriends = circleFriendMapper.findFollowFriends(id);
        baseResp.setData(followFriends);
        baseResp.setCode(200);
        baseResp.setMessage("查询成功");
        return baseResp;
    }
}
