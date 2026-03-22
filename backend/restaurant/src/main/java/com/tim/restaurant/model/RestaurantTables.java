package com.tim.restaurant.model;


import jakarta.persistence.*;

@Entity
@Table(name = "restaurant_table")
public class RestaurantTables {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int seats;

    @Enumerated(EnumType.STRING)
    private Zone zone;

    public RestaurantTables() {}

    public RestaurantTables(String name, int seats, Zone zone) {
        this.seats = seats;
        this.zone = zone;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getSeats() {
        return seats;
    }
    public void setSeats(int seats) {
        this.seats = seats;
    }
    public Zone getZone() {
        return zone;
    }
    public void setZone(Zone zone) {
        this.zone = zone;
    }

}
