package com.gl.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "conf")
@Data
public class Conf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sno;

    private String confname;

    private String link;

    private String fullname;

    private String location;

    private String confimage;

    private Integer up;

    @Column(name = "recordListingID")
    private Integer recordListingId;

    private LocalDate date;

}