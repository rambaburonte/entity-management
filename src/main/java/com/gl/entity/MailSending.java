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
@Table(name = "mail_sending")
@Data
public class MailSending {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "campaign_type")
    private String campaignType;

    @Column(name = "invitation_type")
    private String invitationType;

    @Column(name = "emails_sent")
    private Integer emailsSent;

    @Column(name = "emails_recivied")
    private Integer emailsRecivied;

    private Integer unsubscribed;

    @Column(name = "unique_views")
    private Integer uniqueViews;

    private Integer user;

    private LocalDate date;

}