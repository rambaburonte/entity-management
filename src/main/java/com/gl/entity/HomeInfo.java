package com.gl.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "home_info")
@Data
public class HomeInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer user;

    @Lob
    private String data;

    @Lob
    private String image;

    private String aimage;

    private String pdf;

    @Lob
    private String info3;

    @Lob
    private String info4;

    private Integer status;

    private LocalDate date;

}