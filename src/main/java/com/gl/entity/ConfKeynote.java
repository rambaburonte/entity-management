package com.gl.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "conf_keynote")
@Data
public class ConfKeynote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer kid;

    private Integer conference;

    private String time;

    private String place;

    private Integer user;

    @Lob
    private String break1;

    @Lob
    private String break2;

}