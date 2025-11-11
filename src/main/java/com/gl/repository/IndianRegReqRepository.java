package com.gl.repository;

import com.gl.entity.IndianRegReq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndianRegReqRepository extends JpaRepository<IndianRegReq, Integer> {
}