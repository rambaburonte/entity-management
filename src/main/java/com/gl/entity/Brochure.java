package com.gl.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "brochure")
@Data
public class Brochure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "prof", length = 200, nullable = false)
    private String prof;

    @Column(name = "name", length = 500, nullable = false)
    private String name;

    @Column(name = "email", length = 500, nullable = false)
    private String email;

    @Column(name = "alternate_email", length = 500)
    private String alternateEmail;

    @Column(name = "phone", length = 500, nullable = false)
    private String phone;

    @Column(name = "organization", length = 500, nullable = false)
    private String organization;

    @Column(name = "country", length = 500, nullable = false)
    private String country;

    @Lob
    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "user", nullable = false)
    private Integer user;
}
