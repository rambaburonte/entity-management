package com.gl.entity;

import java.math.BigDecimal;
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
@Table(name = "workshops")
@Data
public class Workshops {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String workshopId;

    private String title;

    private String instructor;

    private LocalDate date;

    private String time;

    private Integer capacity;

    private Integer enrolled;

    private BigDecimal fee;

    private String room;

    @Lob
    private String prerequisites;

    @Lob
    private String description;

    @Lob
    private String topics; // JSON array of topics

    private Integer conferenceId;

    @Column(name = "recordListingID")
    private Integer recordListingId;
}