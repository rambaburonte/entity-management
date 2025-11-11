package com.gl.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "discount_codes")
@Data
public class DiscountCode {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "code", unique = true, length = 50, nullable = false)
    private String code;
    
    @Column(name = "discount_type", length = 20)
    private String discountType; // "percentage" or "fixed"
    
    @Column(name = "discount_value", precision = 10, scale = 2)
    private BigDecimal discountValue;
    
    @Column(name = "conference_id", length = 100)
    private String conferenceId;
    
    @Column(name = "registration_type", length = 50)
    private String registrationType; // NULL means applies to all types
    
    @Column(name = "max_uses")
    private Integer maxUses;
    
    @Column(name = "current_uses")
    private Integer currentUses;
    
    @Column(name = "valid_from")
    private LocalDate validFrom;
    
    @Column(name = "valid_until")
    private LocalDate validUntil;
    
    @Column(name = "is_active")
    private Boolean isActive;
    
    @Column(name = "created_at")
    private LocalDate createdAt;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}
