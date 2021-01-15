package com.qf.dao;

import com.qf.pojo.vo.TV;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TvRepository extends JpaRepository<TV,Integer> {

    List<TV> findByRegion(String region);
}
