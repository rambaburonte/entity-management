package com.gl.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "cfa_categories")
@Data
public class CfaCategories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rec_id")
    private Integer recId;

    @Column(name = "conference_name")
    private String conferenceName;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "order_sequence_no")
    private Byte orderSequenceNo;

}