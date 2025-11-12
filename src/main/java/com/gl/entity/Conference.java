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
@Table(name = "conferences")
@Data
public class Conference {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "name", length = 250)
    private String name;
    
    @Lob
    @Column(name = "theme", columnDefinition = "TEXT")
    private String theme;
    
    @Column(name = "conference_date", length = 100)
    private String conferenceDate;
    
    @Lob
    @Column(name = "website", columnDefinition = "TEXT")
    private String website;
    
    @Lob
    @Column(name = "website1", columnDefinition = "TEXT")
    private String website1;
    
    @Lob
    @Column(name = "abstract", columnDefinition = "TEXT")
    private String abstractInfo;
    
    @Lob
    @Column(name = "abstract1", columnDefinition = "TEXT")
    private String abstract1;
    
    @Lob
    @Column(name = "email", columnDefinition = "TEXT")
    private String email;
    
    @Lob
    @Column(name = "email1", columnDefinition = "TEXT")
    private String email1;
    
    @Lob
    @Column(name = "email2", columnDefinition = "TEXT")
    private String email2;
    
    @Lob
    @Column(name = "short_desc", columnDefinition = "TEXT")
    private String shortDesc;
    
    @Column(name = "ceremony_time", length = 150)
    private String ceremonyTime;
    
    @Column(name = "ceremony_place", length = 150)
    private String ceremonyPlace;
    
    @Column(name = "venue", length = 250)
    private String venue;
}
