package com.gl.repository;

import com.gl.entity.Invitecolleague;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitecolleagueRepository extends JpaRepository<Invitecolleague, Integer> {
}