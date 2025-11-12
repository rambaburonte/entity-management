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
@Table(name = "venue_accommodation")
@Data
public class VenueAccommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "acc_content", columnDefinition = "TEXT")
    @Lob
    private String accContent;

    @Column(name = "acc_image1", length = 500)
    private String accImage1;

    @Column(name = "image_title1", columnDefinition = "TEXT")
    @Lob
    private String imageTitle1;

    @Column(name = "acc_image2", length = 500)
    private String accImage2;

    @Column(name = "image_title2", columnDefinition = "TEXT")
    @Lob
    private String imageTitle2;

    @Column(name = "acc_image3", length = 500)
    private String accImage3;

    @Column(name = "image_title3", columnDefinition = "TEXT")
    @Lob
    private String imageTitle3;

}