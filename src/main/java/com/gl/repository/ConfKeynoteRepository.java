package com.gl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.entity.ConfKeynote;

@Repository
public interface ConfKeynoteRepository extends JpaRepository<ConfKeynote, Integer> {
}