package com.gl.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "daily_tasks")
@Data
public class DailyTasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tid;

    private Integer user;

    @Column(name = "assigned_by")
    private String assignedBy;

    private LocalDate date;

    @Lob
    private String sec1;

    @Lob
    private String sec2;

    @Lob
    private String sec3;

    @Lob
    private String sec4;

    @Lob
    private String sec5;

    @Lob
    private String sec6;

    @Lob
    private String sec7;

    @Lob
    private String sec8;

    @Lob
    private String sec9;

    @Lob
    private String remarks;

    @Column(name = "sec1_reply")
    @Lob
    private String sec1Reply;

    @Column(name = "sec2_reply")
    @Lob
    private String sec2Reply;

    @Column(name = "sec3_reply")
    @Lob
    private String sec3Reply;

    @Column(name = "sec4_reply")
    @Lob
    private String sec4Reply;

    @Column(name = "sec5_reply")
    @Lob
    private String sec5Reply;

    @Column(name = "sec6_reply")
    @Lob
    private String sec6Reply;

    @Column(name = "sec7_reply")
    @Lob
    private String sec7Reply;

    @Column(name = "sec8_reply")
    @Lob
    private String sec8Reply;

    @Column(name = "sec9_reply")
    @Lob
    private String sec9Reply;

}