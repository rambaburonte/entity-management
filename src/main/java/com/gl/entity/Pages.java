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
@Table(name = "pages")
@Data
public class Pages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    private String faqs;

    @Lob
    private String policies;

    @Lob
    private String guidelines;

    @Column(name = "visa_information")
    @Lob
    private String visaInformation;

    @Column(name = "invitation_letter")
    @Lob
    private String invitationLetter;

    @Column(name = "terms_conditions")
    @Lob
    private String termsConditions;

    private Integer user;

}