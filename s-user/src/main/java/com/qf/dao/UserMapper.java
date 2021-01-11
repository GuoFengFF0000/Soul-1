package com.qf.dao;

import com.qf.pojo.vo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface UserMapper {
    List<User> selectAll();

   List<User> selectById(@Param("id") Integer id);

    User selectIdRandom();
}
