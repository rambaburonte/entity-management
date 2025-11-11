package com.gl.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "venue_helpdesk")
@Data
public class VenueHelpdesk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String address;

    private String state;

    private String city;

    private String airport;

    private String distance;

    private String language;

    @Column(name = "helpdesk_image")
    private String helpdeskImage;

    @Column(name = "venue_image")
    private String venueImage;

}