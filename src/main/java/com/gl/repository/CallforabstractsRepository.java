package com.gl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.entity.Callforabstracts;

@Repository
public interface CallforabstractsRepository extends JpaRepository<Callforabstracts, Integer> {
}