package com.gl.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "invitecolleague")
@Data
public class Invitecolleague {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String confTitle1;

    private String confTitle2;

    private String cfaurl;

    @Lob
    private String impSessions;

    private String withRegards;

}