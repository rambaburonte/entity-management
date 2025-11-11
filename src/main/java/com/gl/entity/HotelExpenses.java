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
@Table(name = "hotel_expenses")
@Data
public class HotelExpenses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "no_days_event")
    private Integer noDaysEvent;

    @Column(name = "h_name")
    private String hName;

    @Column(name = "accommodation_per_night")
    private String accommodationPerNight;

    @Column(name = "no_of_rooms")
    private String noOfRooms;

    @Column(name = "accommodation_price")
    private String accommodationPrice;

    @Column(name = "lunch_buffet")
    private String lunchBuffet;

    @Column(name = "t_expenses")
    private String tExpenses;

    @Column(name = "conf_room_price")
    private Integer confRoomPrice;

    @Column(name = "total_price")
    private String totalPrice;

    @Column(name = "imp_id")
    private String impId;

    private LocalDate date;

}