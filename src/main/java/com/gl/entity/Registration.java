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
@Table(name = "registrations")
@Data
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String name;

    private String email;

    private String phone;

    private String country;

    @Lob
    private String address;

    @Lob
    private String org;

    private Double price;

    @Column(name = "checkin_date")
    private String checkinDate;

    private LocalDate date;

    private String conf;

    @Lob
    private String token;

    @Column(name = "confirm_no")
    private String confirmNo;

    private Integer status;

    @Lob
    private String description;

    @Column(name = "payment_type")
    private String paymentType;

    private String category;

    @Column(name = "entity_assigned")
    private String entityAssigned;

    @Column(name = "invoice_number")
    private String invoiceNumber;

    @Column(name = "t_id")
    private String tId;

    private String entity;

}