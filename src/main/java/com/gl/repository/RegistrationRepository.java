package com.gl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.entity.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Integer> {

    List<Registration> findByConf(String conf);

    List<Registration> findByEmail(String email);
}