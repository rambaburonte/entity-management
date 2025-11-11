package com.gl.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "cfa_sub_categories")
@Data
public class CfaSubCategories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rec_id")
    private Integer recId;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "sub_category_name")
    private String subCategoryName;

    @Column(name = "order_sequence_no")
    private Byte orderSequenceNo;

}