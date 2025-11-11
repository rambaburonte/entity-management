package com.gl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.entity.Conf;

@Repository
public interface ConfRepository extends JpaRepository<Conf, Integer> {
}