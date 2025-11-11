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
@Table(name = "positives")
@Data
public class Positives {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "positive_type")
    private String positiveType;

    private String name;

    @Lob
    private String email;

    private String photo;

    private Integer user;

    @Column(name = "recordListingID")
    private Integer recordListingId;

}