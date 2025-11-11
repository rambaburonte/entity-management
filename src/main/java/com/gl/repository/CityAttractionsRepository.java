package com.gl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.entity.CityAttractions;

@Repository
public interface CityAttractionsRepository extends JpaRepository<CityAttractions, Integer> {
}