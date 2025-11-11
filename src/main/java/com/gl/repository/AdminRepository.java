package com.gl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
}