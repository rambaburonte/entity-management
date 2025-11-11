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
@Table(name = "sessions")
@Data
public class Sessions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String sessionId;

    private String title;

    private String time;

    private String type; // keynote, technical, break, panel, social, workshop, registration, ceremony, excursion

    private String room;

    private String track; // structural, geotechnical, transportation, water, construction, environmental

    private String speaker;

    private Integer speakerId;

    @Lob
    private String description;

    @Lob
    private String papers; // JSON array of paper titles

    private Integer day; // 1, 2, 3

    private String date; // YYYY-MM-DD

    private Integer conferenceId;

    @Column(name = "recordListingID")
    private Integer recordListingId;
}