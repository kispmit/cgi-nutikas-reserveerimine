package com.tim.restaurant.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "table_id")

    private RestaurantTable table;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private int peoplecount;

    public Reservation() {}

    public Reservation(RestaurantTable table, LocalDateTime startTime, LocalDateTime endTime, int peoplecount) {
        this.table = table;
        this.startTime = startTime;
        this.endTime = endTime;
        this.peoplecount = peoplecount;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public RestaurantTable getTable() {
        return table;
    }
    public void setTable(RestaurantTable table) {
        this.table = table;
    }
    public LocalDateTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
    public int getPeoplecount() {
        return peoplecount;
    }
    public void setPeoplecount(int peoplecount) {
        this.peoplecount = peoplecount;
    }

}
