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
@Table(name = "abstract_submission")
@Data
public class AbstractSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user")
    private String user;

    private String title;

    private String fname;

    private String country;

    @Lob
    private String org;

    private String email;

    private String phno;

    private String category;

    @Column(name = "sent_from", length = 500)
    private String sentFrom;

    @Column(name = "track_name")
    private String trackName;

    @Lob
    private String address;

    private LocalDate date;

    private String ipaddress;

    private String attachment;

    @Column(name = "presentation_title")
    @Lob
    private String presentationTitle;

    @Column(name = "entity", length = 255)
    private String entity;

}