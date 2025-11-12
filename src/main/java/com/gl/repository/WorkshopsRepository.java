package com.gl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.entity.Workshops;

@Repository
public interface WorkshopsRepository extends JpaRepository<Workshops, Integer> {
    List<Workshops> findByUser(Integer user);
}