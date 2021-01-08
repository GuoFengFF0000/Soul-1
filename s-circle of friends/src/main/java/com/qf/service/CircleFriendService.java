package com.qf.service;

import com.qf.pojo.CircleFriends;
import com.qf.pojo.resp.BaseResp;
import org.apache.ibatis.annotations.Param;

public interface CircleFriendService {
    BaseResp insertComment(CircleFriends circleFriends);


    BaseResp circleFriendFindAllByNew();

    BaseResp circleFriendFindAllByNewByZanNums();

    BaseResp addZanNums(@Param("cfId")Integer cfId);

    BaseResp adminFriendAdd(CircleFriends circleFriends);

    BaseResp adminFriendDel(@Param("cfId") Integer cfId);


    BaseResp adminFriendUpdate(CircleFriends circleFriends);

    BaseResp adminFriendFindAll();

    BaseResp adminFriendFindByCfId(@Param("cfId") Integer cfId);
}
