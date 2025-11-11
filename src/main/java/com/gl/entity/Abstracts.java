package com.gl.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "abstracts")
@Data
public class Abstracts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sno;

    @Column(name = "Title", length = 50, nullable = false)
    private String title;

    @Column(name = "Name", length = 50, nullable = false)
    private String name;

    @Column(name = "Surname", length = 50, nullable = false)
    private String surname;

    @Column(name = "Country", length = 20, nullable = false)
    private String country;

    @Column(name = "Authors_Email", length = 50, nullable = false)
    private String authorsEmail;

    @Column(name = "Alternative_Email", length = 50, nullable = false)
    private String alternativeEmail;

    @Column(name = "Abstract_Category", length = 80, nullable = false)
    private String abstractCategory;

    @Column(name = "Abstract", length = 80, nullable = false)
    private String abstractField;

    @Column(name = "Full_Postal_Address", length = 200, nullable = false)
    private String fullPostalAddress;

    @Column(name = "Attach_your_file", length = 50, nullable = false)
    private String attachYourFile;

}