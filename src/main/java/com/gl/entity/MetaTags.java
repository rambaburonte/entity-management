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
@Table(name = "meta_tags")
@Data
public class MetaTags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer user;

    @Column(name = "main_title")
    private String mainTitle;

    @Lob
    private String description;

    @Lob
    private String keywords;

    @Column(name = "abs_title")
    private String absTitle;

    @Column(name = "abs_description")
    @Lob
    private String absDescription;

    @Column(name = "abs_keywords")
    @Lob
    private String absKeywords;

    @Column(name = "reg_title")
    private String regTitle;

    @Column(name = "reg_description")
    @Lob
    private String regDescription;

    @Column(name = "reg_keywords")
    @Lob
    private String regKeywords;

    @Column(name = "guidelines_title")
    private String guidelinesTitle;

    @Column(name = "guidelines_description")
    @Lob
    private String guidelinesDescription;

    @Column(name = "guidelines_keywords")
    @Lob
    private String guidelinesKeywords;

    @Column(name = "policies_title")
    private String policiesTitle;

    @Column(name = "policies_description")
    @Lob
    private String policiesDescription;

    @Column(name = "policies_keywords")
    @Lob
    private String policiesKeywords;

    @Column(name = "contact_title")
    private String contactTitle;

    @Column(name = "contact_description")
    @Lob
    private String contactDescription;

    @Column(name = "contact_keywords")
    @Lob
    private String contactKeywords;

    @Column(name = "speaker_title")
    private String speakerTitle;

    @Column(name = "speaker_description")
    @Lob
    private String speakerDescription;

    @Column(name = "speaker_keywords")
    @Lob
    private String speakerKeywords;

    @Column(name = "committee_title")
    private String committeeTitle;

    @Column(name = "committee_description")
    @Lob
    private String committeeDescription;

    @Column(name = "committee_keywords")
    @Lob
    private String committeeKeywords;

    @Column(name = "callforpapers_title")
    private String callforpapersTitle;

    @Column(name = "callforpapers_description")
    @Lob
    private String callforpapersDescription;

    @Column(name = "callforpapers_keywords")
    @Lob
    private String callforpapersKeywords;

    @Column(name = "topics_title")
    private String topicsTitle;

    @Column(name = "topics_description")
    @Lob
    private String topicsDescription;

    @Column(name = "topics_keywords")
    @Lob
    private String topicsKeywords;

    @Column(name = "venue_title")
    private String venueTitle;

    @Column(name = "venue_description")
    @Lob
    private String venueDescription;

    @Column(name = "venue_keywords")
    @Lob
    private String venueKeywords;

    @Column(name = "visa_title")
    private String visaTitle;

    @Column(name = "visa_description")
    @Lob
    private String visaDescription;

    @Column(name = "visa_keywords")
    @Lob
    private String visaKeywords;

    @Column(name = "invitation_title")
    private String invitationTitle;

    @Column(name = "invitation_description")
    @Lob
    private String invitationDescription;

    @Column(name = "invitation_keywords")
    @Lob
    private String invitationKeywords;

    @Column(name = "terms_title")
    private String termsTitle;

    @Column(name = "terms_description")
    @Lob
    private String termsDescription;

    @Column(name = "terms_keywords")
    @Lob
    private String termsKeywords;

}