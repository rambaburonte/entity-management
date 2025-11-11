package com.gl.repository;

import com.gl.entity.InvitedSpeakers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitedSpeakersRepository extends JpaRepository<InvitedSpeakers, Integer> {
}