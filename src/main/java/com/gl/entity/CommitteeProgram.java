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
@Table(name = "committee_program")
@Data
public class CommitteeProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Lob
    private String affiliation;

    private String photo;

    @Lob
    private String biography;

    @Lob
    private String research;

    private Integer user;

    @Column(name = "recordListingID")
    private Integer recordListingId;

}