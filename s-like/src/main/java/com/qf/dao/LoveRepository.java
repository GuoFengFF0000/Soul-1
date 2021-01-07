package com.qf.dao;

import com.qf.pojo.vo.Love;
import com.qf.pojo.vo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LoveRepository extends JpaRepository<Love,Integer>{

    public Love findByLikeIdAndUserId(Integer likeId, Integer userId);

    List<Love> findByUserId(Integer userId);

    List<Love> findByLikeId(Integer likeId);
}
