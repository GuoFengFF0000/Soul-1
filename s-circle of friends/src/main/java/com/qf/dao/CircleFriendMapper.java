package com.qf.dao;

import com.qf.pojo.vo.CircleFriends;
import com.qf.pojo.vo.Follow;
import com.qf.pojo.vo.Remark;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

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

    int insertRemark(Remark remark);

    List findAllRemarkByCfId(@Param("cfId") Integer cfId);

    int addFollow(Follow follow);

    int findFollowByUid(@Param("uid") Integer uid);

    int findFollowByFollowUser(@Param("followUser") String followUser);

    List findFollowFriends(@Param("id")Integer id);

    List findFollowUserByUid(@Param("uid") Integer uid);

    CircleFriends findByCfId(@Param("cfId") Integer cfId);

    @Update("set names utf8mb4")
    public void setCharsetToUtf8mb4();


}
