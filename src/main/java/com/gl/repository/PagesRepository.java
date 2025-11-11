package com.gl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.entity.Pages;

@Repository
public interface PagesRepository extends JpaRepository<Pages, Integer> {
    Pages findByUser(Integer user);
}