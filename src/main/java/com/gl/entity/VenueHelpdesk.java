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

    @Column(name = "address", length = 200)
    private String address;

    @Column(name = "state", length = 40)
    private String state;

    @Column(name = "city", length = 40)
    private String city;

    @Column(name = "Airport", length = 70)
    private String airport;

    @Column(name = "Distance", length = 15)
    private String distance;

    @Column(name = "language", length = 30)
    private String language;

    @Column(name = "helpdesk_image", length = 50)
    private String helpdeskImage;

    @Column(name = "venue_image", length = 50)
    private String venueImage;

}