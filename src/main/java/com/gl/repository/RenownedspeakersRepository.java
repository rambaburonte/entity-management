package com.gl.repository;

import com.gl.entity.Renownedspeakers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RenownedspeakersRepository extends JpaRepository<Renownedspeakers, Integer> {
}