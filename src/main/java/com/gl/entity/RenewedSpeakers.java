package com.gl.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "renewed_speakers")
@Data
public class RenewedSpeakers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer user;

    private String photo;

    private String name;

    @Lob
    private String affiliation;

    private String country;

    private LocalDate date;

    private Integer status;

    @Column(name = "recordListingID")
    private Integer recordListingId;

}