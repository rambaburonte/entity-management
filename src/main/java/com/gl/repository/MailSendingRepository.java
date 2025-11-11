package com.gl.repository;

import com.gl.entity.MailSending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailSendingRepository extends JpaRepository<MailSending, Integer> {
}