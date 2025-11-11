package com.gl.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "keynotes")
@Data
public class Keynotes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String conference;

    private String name;

    private String designation;

    private String organization;

    private String country;

    private String photo;

    @Lob
    private String bio;

    @Lob
    private String abstracts;

}