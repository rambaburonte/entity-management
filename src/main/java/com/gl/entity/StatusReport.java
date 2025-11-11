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
@Table(name = "status_report")
@Data
public class StatusReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "abstract_received")
    private Integer abstractReceived;

    @Column(name = "regs_received")
    private Integer regsReceived;

    @Column(name = "abstracts_updated")
    private Integer abstractsUpdated;

    @Column(name = "local_name")
    private String localName;

    @Column(name = "local_affiliation")
    @Lob
    private String localAffiliation;

    @Column(name = "local_email")
    private String localEmail;

    @Column(name = "nonlocal_name")
    private String nonlocalName;

    @Column(name = "nonlocal_affiliation")
    private String nonlocalAffiliation;

    @Column(name = "nonlocal_email")
    private String nonlocalEmail;

    private Integer user;

    @Column(name = "status_date")
    private LocalDate statusDate;

}