package com.qf.dao;

import com.qf.pojo.vo.Gift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiftRepository extends JpaRepository<Gift,Integer> {
}
