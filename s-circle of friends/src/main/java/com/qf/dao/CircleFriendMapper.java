package com.qf.dao;

import com.qf.pojo.CircleFriends;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CircleFriendMapper {

    int insertComment(CircleFriends circleFriends);

    List circleFriendFindAllByNew();

    List circleFriendFindAllByNewByZanNums();

    int addZanNums(@Param("cfId")Integer cfId);

    int adminFriendAdd(CircleFriends circleFriends);

    int adminFriendDel(@Param("cfId") Integer cfId);

    int adminFriendUpdate(CircleFriends circleFriends);

    List adminFriendFindAll();

    CircleFriends adminFriendFindByCfId(@Param("cfId") Integer cfId);
}
