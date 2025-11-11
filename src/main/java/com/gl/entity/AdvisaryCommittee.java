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
@Table(name = "advisary_committee")
@Data
public class AdvisaryCommittee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Lob
    @Column(name = "affiliation", nullable = false)
    private String affiliation;

    @Column(name = "photo", length = 100)
    private String photo;

    @Lob
    @Column(name = "biography")
    private String biography;

    @Lob
    @Column(name = "research")
    private String research;

    @Lob
    @Column(name = "pub1", nullable = false)
    private String pub1;

    @Lob
    @Column(name = "pub2", nullable = false)
    private String pub2;

    @Lob
    @Column(name = "pub3", nullable = false)
    private String pub3;

    @Lob
    @Column(name = "pub4", nullable = false)
    private String pub4;

    @Lob
    @Column(name = "network", nullable = false)
    private String network;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "user", nullable = false)
    private Integer user;

    @Column(name = "recordListingID")
    private Integer recordListingId;

}