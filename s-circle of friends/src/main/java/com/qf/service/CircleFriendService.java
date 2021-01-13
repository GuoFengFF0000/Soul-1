package com.qf.service;

import com.qf.pojo.vo.CircleFriends;
import com.qf.pojo.resp.BaseResp;
import com.qf.pojo.vo.Follow;
import com.qf.pojo.vo.Remark;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;

public interface CircleFriendService {
    BaseResp insertComment(CircleFriends circleFriends);


    BaseResp circleFriendFindAllByNew();

    BaseResp circleFriendFindAllByNewByZanNums();

    BaseResp addZanNums(@Param("cfId")Integer cfId,HttpServletRequest request);

    BaseResp adminFriendAdd(CircleFriends circleFriends);

    BaseResp adminFriendDel(@Param("cfId") Integer cfId);

    BaseResp adminFriendUpdate(CircleFriends circleFriends);

    BaseResp adminFriendFindAll();

    BaseResp adminFriendFindByCfId(@Param("cfId") Integer cfId);

    BaseResp findById(HttpServletRequest request);

    BaseResp insertRemark(Remark remark,HttpServletRequest request);


    BaseResp findAllRemarkByCfId(@Param("cfId") Integer cfId);

    BaseResp addFollow(Follow follow);

    BaseResp findFollowByUid(@Param("uid") Integer f_uid);

    BaseResp findFollowByFollowUser(@Param("followUser")String followUser);

    BaseResp findFollowFriends(@Param("id")Integer id);
}
