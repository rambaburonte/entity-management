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
@Table(name = "committee")
@Data
public class Committee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 100)
    private String name;

    @Lob
    @Column(name = "affiliation", columnDefinition = "TEXT")
    private String affiliation;

    @Column(name = "photo", length = 100)
    private String photo;

    @Lob
    @Column(name = "biography", columnDefinition = "TEXT")
    private String biography;

    @Lob
    @Column(name = "research", columnDefinition = "TEXT")
    private String research;

    @Lob
    @Column(name = "pub1", columnDefinition = "TEXT")
    private String pub1;

    @Lob
    @Column(name = "pub2", columnDefinition = "TEXT")
    private String pub2;

    @Lob
    @Column(name = "pub3", columnDefinition = "TEXT")
    private String pub3;

    @Lob
    @Column(name = "pub4", columnDefinition = "TEXT")
    private String pub4;

    @Lob
    @Column(name = "network", columnDefinition = "TEXT")
    private String network;

    @Column(name = "user")
    private Integer user;

    @Column(name = "recordListingID")
    private Integer recordListingId;
}