package com.gl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.entity.Sponsors;

@Repository
public interface SponsorsRepository extends JpaRepository<Sponsors, Integer> {
    List<Sponsors> findByUser(String user);
}