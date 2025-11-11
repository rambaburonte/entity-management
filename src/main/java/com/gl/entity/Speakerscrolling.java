package com.gl.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "speakerscrolling")
@Data
public class Speakerscrolling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Assuming we add an id for JPA

    private String name;

    private String country;

    private String affiliation;

    private String image;

}