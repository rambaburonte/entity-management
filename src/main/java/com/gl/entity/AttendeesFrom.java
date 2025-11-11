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
@Table(name = "attendees_from")
@Data
public class AttendeesFrom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user", nullable = false)
    private Integer user;

    @Column(name = "image", length = 500)
    private String image;

    @Column(name = "heading", length = 500, nullable = false)
    private String heading;

    @Lob
    @Column(name = "para", nullable = false)
    private String para;

    @Column(name = "recordListingID")
    private Integer recordListingID;

    @Column(name = "link", length = 500)
    private String link;
}
