package com.gl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.entity.AcadBusiness;

@Repository
public interface AcadBusinessRepository extends JpaRepository<AcadBusiness, Integer> {
}