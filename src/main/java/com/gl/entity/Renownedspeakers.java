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
@Table(name = "renownedspeakers")
@Data
public class Renownedspeakers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer user;

    private String name1;

    private String photo1;

    @Lob
    private String affiliation1;

    private String country1;

    private String name2;

    private String photo2;

    @Lob
    private String affiliation2;

    private String country2;

    private String tdate;

    private Integer status;

    @Lob
    private String biography1;

    @Lob
    private String biography2;

    @Column(name = "recordListingID")
    private Integer recordListingId;

}