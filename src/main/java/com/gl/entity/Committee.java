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

    private String name;

    @Lob
    private String affiliation;

    private String email;

    private String photo;

    @Lob
    private String biography;

    @Lob
    private String research;

    @Lob
    @Column(name = "abstract")
    private String abstractField;

    private String category;

    private Integer user;

    @Column(name = "recordListingID")
    private Integer recordListingId;

}