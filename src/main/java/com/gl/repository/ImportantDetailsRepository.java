package com.gl.repository;

import com.gl.entity.ImportantDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportantDetailsRepository extends JpaRepository<ImportantDetails, Integer> {
}