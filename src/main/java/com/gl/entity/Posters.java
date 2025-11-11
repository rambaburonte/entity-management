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
@Table(name = "posters")
@Data
public class Posters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer user;

    private String name;

    @Lob
    private String affiliation;

    private String pdf;

    private String photo;

    private String logo;

    @Column(name = "recordListingID")
    private Integer recordListingId;

    @Lob
    private String biography;

    @Lob
    @Column(name = "abstract")
    private String abstractField;

}