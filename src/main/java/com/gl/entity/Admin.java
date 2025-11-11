package com.gl.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "admin")
@Data
public class Admin {

    @Id
    @Column(name = "username", length = 45)
    private String username;

    @Column(name = "password", length = 45)
    private String password;

    @Column(name = "login")
    private LocalDateTime login;

    @Column(name = "email", length = 60)
    private String email;

}