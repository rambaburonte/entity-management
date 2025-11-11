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
@Table(name = "messages")
@Data
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "msg_id")
    private Integer msgId;

    private Integer user;

    @Column(name = "speaker_name")
    private String speakerName;

    @Lob
    private String affiliation;

    @Column(name = "reply_given")
    @Lob
    private String replyGiven;

    @Column(name = "reply_date")
    private LocalDate replyDate;

    @Column(name = "reply_attachment")
    private String replyAttachment;

    @Column(name = "reply_sent")
    @Lob
    private String replySent;

    @Column(name = "sent_date")
    private LocalDate sentDate;

    @Column(name = "sent_attachment")
    private String sentAttachment;

    @Column(name = "abstract_title")
    private String abstractTitle;

    private LocalDate date;

}