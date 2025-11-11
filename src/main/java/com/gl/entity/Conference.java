package com.gl.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "conference")
@Data
public class Conference {
    
    @Id
    @Column(name = "user", length = 100)
    private String user; // Used as primary key (conference identifier)
    
    @Column(name = "conf_name", length = 255)
    private String confName;
    
    @Column(name = "location", length = 100)
    private String location;
    
    @Column(name = "country", length = 50)
    private String country;
    
    @Column(name = "conference_date")
    private LocalDate conferenceDate;
    
    @Column(name = "early_bird_date")
    private LocalDate earlyBirdDate;
    
    @Column(name = "standard_date")
    private LocalDate standardDate;
    
    @Column(name = "abstract_date")
    private LocalDate abstractDate;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "website", length = 255)
    private String website;
    
    @Column(name = "email", length = 100)
    private String email;
    
    @Column(name = "phone", length = 20)
    private String phone;
    
    @Column(name = "created_at")
    private LocalDate createdAt;
    
    @Column(name = "updated_at")
    private LocalDate updatedAt;
}
