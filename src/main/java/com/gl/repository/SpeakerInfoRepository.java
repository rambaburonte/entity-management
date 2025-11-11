package com.gl.repository;

import com.gl.entity.SpeakerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeakerInfoRepository extends JpaRepository<SpeakerInfo, Integer> {
}