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
@Table(name = "tracks")
@Data
public class Tracks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "track_name")
    @Lob
    private String trackName;

    @Column(name = "session_name", length = 200)
    private String sessionName;

    @Column(name = "session_affiliation")
    @Lob
    private String sessionAffiliation;

    @Column(name = "session_photo", length = 120)
    private String sessionPhoto;

    @Column(name = "session_logo", length = 120)
    private String sessionLogo;

    @Column(name = "cosession_name", length = 200)
    private String cosessionName;

    @Column(name = "cosession_affiliation")
    @Lob
    private String cosessionAffiliation;

    @Column(name = "cosession_photo", length = 120)
    private String cosessionPhoto;

    @Column(name = "cosession_logo", length = 120)
    private String cosessionLogo;

    @Column(name = "sa_name", length = 100)
    private String saName;

    @Column(name = "sa_affiliation")
    @Lob
    private String saAffiliation;

    @Column(name = "sa_photo", length = 80)
    private String saPhoto;

    @Column(name = "sa_logo", length = 80)
    private String saLogo;

    private Integer sid;

    @Column(name = "track_time", length = 100)
    private String trackTime;

    @Column(name = "track_place", length = 100)
    private String trackPlace;

    @Column(name = "track_date")
    private LocalDate trackDate;

    private Integer user;

    @Column(name = "recordListingID")
    private Integer recordListingId;

}