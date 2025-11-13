package com.gl.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "important_details")
@Data
public class ImportantDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sno;
    
    private Integer id;

    @Column(name = "ShortName")
    private String shortName;

    @Column(name = "ConfUrl")
    private String confUrl;

    @Column(name = "Theme")
    private String theme;

    @Column(name = "EmailId1")
    private String emailId1;

    @Column(name = "abstract_submission_deadline")
    private String abstractSubmissionDeadline;

    @Column(name = "abstract_submission_opens")
    private String abstractSubmissionOpens;

    @Column(name = "registration_opens")
    private String registrationOpens;

    @Column(name = "EarlyBird")
    private String earlyBird;

    @Column(name = "mid_term")
    private String midTerm;

    @Column(name = "Late_registration")
    private String lateRegistration;

    @Column(name = "OnSpot")
    private String onSpot;

    @Column(name = "ConferenceTitle")
    private String conferenceTitle;

    @Column(name = "ConferenceVenue")
    private String conferenceVenue;

    @Column(name = "ConferenceDates")
    private String conferenceDates;

    @Column(name = "facebook_link")
    private String facebookLink;

    @Column(name = "linkedin_link")
    private String linkedinLink;

    @Column(name = "instagram_link")
    private String instagramLink;

    @Column(name = "twitter_link")
    private String twitterLink;

    @Column(name = "twitter_tweets")
    @Lob
    private String twitterTweets;

    private String date;

    @Column(name = "pc_name")
    private String pcName;

    @Column(name = "entity_logo", length = 500)
    private String entityLogo;

    @Column(name = "EmailId2", length = 500)
    private String emailId2;

    @Column(name = "EmailId3", length = 500)
    private String emailId3;

}
