package com.gl.repository;

import com.gl.entity.TzMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TzMembersRepository extends JpaRepository<TzMembers, Integer> {
}