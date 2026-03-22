package com.tim.restaurant.model;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "table_id")

    private RestaurantTable table;

    private Date startTime;
    private Date endTime;

    private int peoplecount;

    public Reservation() {}

    public Reservation(RestaurantTable table, Date startTime, Date endTime, int peoplecount) {
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
    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public Date getEndTime() {
        return endTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    public int getPeoplecount() {
        return peoplecount;
    }
    public void setPeoplecount(int peoplecount) {
        this.peoplecount = peoplecount;
    }

}
