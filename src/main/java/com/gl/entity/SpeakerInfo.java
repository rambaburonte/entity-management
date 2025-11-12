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
@Table(name = "speaker_info")
@Data
public class SpeakerInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "sub_track")
    private Integer subTrack;

    @Column(name = "time", length = 50)
    private String time;

    @Column(name = "speaker_title")
    @Lob
    private String speakerTitle;

    @Column(name = "speaker_name", length = 200)
    private String speakerName;

    @Column(name = "speaker_photo", length = 100)
    private String speakerPhoto;

    @Column(name = "speaker_logo", length = 100)
    private String speakerLogo;

    @Column(name = "spk_pdf", length = 100)
    private String spkPdf;

    @Column(name = "speaker_affiliation")
    @Lob
    private String speakerAffiliation;

    @Column(name = "plenary_title")
    @Lob
    private String plenaryTitle;

    @Column(name = "time_1", length = 50)
    private String time1;

    @Column(name = "plenary_name", length = 150)
    private String plenaryName;

    @Column(name = "plenary_affiliation")
    @Lob
    private String plenaryAffiliation;

    @Column(name = "plenary_photo", length = 100)
    private String plenaryPhoto;

    @Column(name = "plenary_logo", length = 100)
    private String plenaryLogo;

    @Column(name = "plenary_pdf", length = 100)
    private String plenaryPdf;

    @Lob
    @Column(name = "break", columnDefinition = "TEXT")
    private String breakField;

    private Integer user;

    @Column(name = "recordListingID")
    private Integer recordListingId;

    @Column(name = "display")
    private Integer display;

}