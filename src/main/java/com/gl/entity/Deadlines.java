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
@Table(name = "deadlines")
@Data
public class Deadlines {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer did;

    private Integer user;

    @Lob
    private String deadlines;

    private LocalDate date;

}