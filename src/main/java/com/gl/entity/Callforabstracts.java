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
@Table(name = "callforabstracts")
@Data
public class Callforabstracts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "recordListingID")
    private Integer recordListingId;

    @Lob
    @Column(name = "TrackName")
    private String trackName;

    @Column(name = "Category", length = 50)
    private String category;

    @Column(name = "user")
    private Integer user;

    @Column(name = "SubTof", length = 200)
    private String subTof;

    @Column(name = "TrackIdentity", length = 40)
    private String trackIdentity;

    @Lob
    @Column(name = "description")
    private String description;

}