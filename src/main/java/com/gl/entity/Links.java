package com.gl.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Links")
@Data
public class Links {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Assuming we add an id for JPA

    private String rss;

    private String facebook;

    private String linkedIn;

    private String twitter;

    private String blog;

}