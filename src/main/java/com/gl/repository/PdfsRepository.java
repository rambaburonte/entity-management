package com.gl.repository;

import com.gl.entity.Pdfs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PdfsRepository extends JpaRepository<Pdfs, Integer> {
}