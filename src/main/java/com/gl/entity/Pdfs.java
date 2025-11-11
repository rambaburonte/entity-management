package com.gl.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "pdfs")
@Data
public class Pdfs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sno;

    @Column(name = "tentative_program")
    private String tentativeProgram;

    private String conferenceguide;

    private String brochure;

    @Column(name = "abstract_book")
    private String abstractBook;

    @Column(name = "eform_usa")
    private String eformUsa;

    private String sponsorship;

    private String eform;

    private Integer id;

}