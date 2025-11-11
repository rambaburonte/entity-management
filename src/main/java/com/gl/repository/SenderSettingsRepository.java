package com.gl.repository;

import com.gl.entity.SenderSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SenderSettingsRepository extends JpaRepository<SenderSettings, Integer> {
}