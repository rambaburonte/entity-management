package com.gl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.entity.Abstracts;

@Repository
public interface AbstractsRepository extends JpaRepository<Abstracts, Integer> {
}