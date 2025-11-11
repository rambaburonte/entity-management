package com.gl.repository;

import com.gl.entity.Posters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostersRepository extends JpaRepository<Posters, Integer> {
}