package com.gl.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "likes")
@Data
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String facebook;

    private String linkedin;

    private String twitter;

    private String blog;

    private String youtube;

    private String flickr;

    private String googleplus;

    private String pinterest;

    private String rss;

}