package com.gl.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "sponsors_mobileapp")
@Data
public class SponsorsMobileapp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Assuming we add an id for JPA

    @Lob
    private String content;

}