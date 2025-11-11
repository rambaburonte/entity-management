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
@Table(name = "AcadBusiness")
@Data
public class AcadBusiness {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EID")
    private Integer eid;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "category", length = 50)
    private String category;

    @Column(name = "id")
    private Integer id;

}