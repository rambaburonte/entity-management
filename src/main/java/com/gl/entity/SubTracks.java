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
@Table(name = "sub_tracks")
@Data
public class SubTracks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "track_id")
    private Integer trackId;

    @Column(name = "session_name")
    private String sessionName;

    @Column(name = "session_affiliation")
    @Lob
    private String sessionAffiliation;

    @Column(name = "session_photo")
    private String sessionPhoto;

    @Column(name = "session_logo")
    private String sessionLogo;

    @Column(name = "cosession_name")
    private String cosessionName;

    @Column(name = "cosession_affiliation")
    @Lob
    private String cosessionAffiliation;

    @Column(name = "cosession_photo")
    private String cosessionPhoto;

    @Column(name = "cosession_logo")
    private String cosessionLogo;

    @Column(name = "strack_name")
    @Lob
    private String strackName;

    @Column(name = "strack_place")
    private String strackPlace;

    @Column(name = "strack_date")
    private LocalDate strackDate;

    @Column(name = "strack_time")
    private String strackTime;

    private Integer user;

    @Column(name = "recordListingID")
    private Integer recordListingId;

}