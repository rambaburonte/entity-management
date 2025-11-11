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
@Table(name = "track_speakers")
@Data
public class TrackSpeakers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer track;

    private String time;

    @Column(name = "speaker_title")
    @Lob
    private String speakerTitle;

    @Column(name = "speaker_name")
    private String speakerName;

    @Column(name = "speaker_photo")
    private String speakerPhoto;

    @Column(name = "speaker_logo")
    private String speakerLogo;

    private String spkPdf;

    @Column(name = "speaker_affiliation")
    @Lob
    private String speakerAffiliation;

    @Column(name = "plenary_title")
    @Lob
    private String plenaryTitle;

    @Column(name = "time_1")
    private String time1;

    @Column(name = "plenary_name")
    private String plenaryName;

    @Column(name = "plenary_affiliation")
    @Lob
    private String plenaryAffiliation;

    @Column(name = "plenary_photo")
    private String plenaryPhoto;

    @Column(name = "plenary_logo")
    private String plenaryLogo;

    @Column(name = "plenary_pdf")
    private String plenaryPdf;

    @Lob
    private String breakField;

    private Integer user;

    @Column(name = "recordListingID")
    private Integer recordListingId;

    @Lob
    private String biography;

    @Lob
    @Column(name = "abstract")
    private String abstractField;

}