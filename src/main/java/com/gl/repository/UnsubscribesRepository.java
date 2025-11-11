package com.gl.repository;

import com.gl.entity.Unsubscribes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnsubscribesRepository extends JpaRepository<Unsubscribes, Integer> {
}