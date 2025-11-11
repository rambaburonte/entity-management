package com.gl.repository;

import com.gl.entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembersRepository extends JpaRepository<Members, Integer> {
    List<Members> findByUser(Integer user);
    List<Members> findByCategoryAndUser(String category, Integer user);
}