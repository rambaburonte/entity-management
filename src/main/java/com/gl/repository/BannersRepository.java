package com.gl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.entity.Banners;

@Repository
public interface BannersRepository extends JpaRepository<Banners, Integer> {
}