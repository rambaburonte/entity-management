package com.gl.repository;

import com.gl.entity.HomeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeInfoRepository extends JpaRepository<HomeInfo, Integer> {
}