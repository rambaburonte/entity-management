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
@Table(name = "time_management")
@Data
public class TimeManagement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "work_done")
    @Lob
    private String workDone;

    @Column(name = "target_date")
    private LocalDate targetDate;

    @Column(name = "finished_date")
    private LocalDate finishedDate;

    private Integer user;

    private LocalDate date;

    @Column(name = "assigned_by")
    private String assignedBy;

    @Column(name = "work_status")
    @Lob
    private String workStatus;

}