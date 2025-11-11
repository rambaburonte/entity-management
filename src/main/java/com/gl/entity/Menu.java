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
@Table(name = "menu")
@Data
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Organizing_Committee")
    private String organizingCommittee;

    @Lob
    private String b2b;

    @Column(name = "scientific_partnering")
    private String scientificPartnering;

    @Column(name = "additional_menu")
    private String additionalMenu;

}