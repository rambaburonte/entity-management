package com.gl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.entity.CityGuideImages;

@Repository
public interface CityGuideImagesRepository extends JpaRepository<CityGuideImages, Integer> {
}