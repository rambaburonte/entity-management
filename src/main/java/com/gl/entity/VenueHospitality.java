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
@Table(name = "venue_hospitality")
@Data
public class VenueHospitality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    @Column(name = "map", columnDefinition = "TEXT")
    private String map;

    @Column(name = "venue_content", columnDefinition = "TEXT")
    @Lob
    private String venueContent;

    @Column(name = "venue_image1", length = 500)
    private String venueImage1;

    @Column(name = "image_title1", columnDefinition = "TEXT")
    @Lob
    private String imageTitle1;

    @Column(name = "venue_image2", length = 500)
    private String venueImage2;

    @Column(name = "image_title2", columnDefinition = "TEXT")
    @Lob
    private String imageTitle2;

    @Column(name = "venue_image3", length = 500)
    private String venueImage3;

    @Column(name = "image_title3", columnDefinition = "TEXT")
    @Lob
    private String imageTitle3;

}