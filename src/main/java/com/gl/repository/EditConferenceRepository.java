package com.gl.repository;

import com.gl.entity.EditConference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditConferenceRepository extends JpaRepository<EditConference, Integer> {
}