package com.gl.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "sponsors")
@Data
public class Sponsors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sponsor_id")
    private Integer sponsorId;

    @Column(name = "conf_id")
    private Integer confId;

    private String name;

    private String logo;

    private String website;

    private String description;

    @Column(name = "sponsor_type")
    private String sponsorType;

}