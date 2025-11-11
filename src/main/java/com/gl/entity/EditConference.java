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
@Table(name = "editConference")
@Data
public class EditConference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String shortName;

    private Integer up;

    private String title;

    private String venue;

    private String hvenue;

    private String dates;

    private String theme;

    @Column(name = "f_Theme")
    private String fTheme;

    private String emailId1;

    private String emailId2;

    private String emailId3;

    private String journalName1;

    private String journalName2;

    private String journalName3;

    private String journalShortName1;

    private String journalShortName2;

    private String journalShortName3;

    private String journalURL1;

    private String journalURL2;

    private String journalURL3;

    private String webUrl;

    private String earlyBird;

    private String regCloses;

    private String finalDate;

    private Integer tracksCount;

    private Integer subTracksCount;

    private String confvenue;

    private String cancellationDate;

    private String researchField;

    @Lob
    private String abstractSub;

    @Lob
    private String confUrl;

    private String description;

    private String field;

    private LocalDate date;

}