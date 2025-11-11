package com.gl.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "work_update")
@Data
public class WorkUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "individual_sent")
    private Integer individualSent;

    @Column(name = "merge_sent")
    private Integer mergeSent;

    private Integer collection;

    private Integer positives;

    @Column(name = "virtual_res")
    private Integer virtualRes;

    private Integer abstracts;

    private Integer registrations;

    private Integer revenue;

    private LocalDate date;

    private String user;

    @Column(name = "update_file")
    private String updateFile;

}