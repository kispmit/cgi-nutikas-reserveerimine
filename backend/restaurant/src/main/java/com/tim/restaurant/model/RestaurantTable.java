package com.tim.restaurant.model;


import jakarta.persistence.*;

@Entity
@Table(name = "restaurant_table")
public class RestaurantTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int seats;

    @Enumerated(EnumType.STRING)
    private Zone zone;

    private boolean quiet;
    private boolean windowSeat;
    private boolean kids;

    public RestaurantTable() {}

    public RestaurantTable(int seats, Zone zone, boolean quietCorner, boolean windowSeat, boolean nearKidsArea) {
        this.seats = seats;
        this.zone = zone;
        this.quiet = quietCorner;
        this.windowSeat = windowSeat;
        this.kids = nearKidsArea;
    }
    public RestaurantTable(int seats, Zone zone) {
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
    public boolean isQuiet() { return quiet;}
    public void setQuiet(boolean quiet) {this.quiet = quiet;}
    public boolean isWindowSeat() { return windowSeat;}
    public void setWindowSeat(boolean windowSeat) {this.windowSeat = windowSeat;}
    public boolean isKids() { return kids;}
    public void setKids(boolean kids) {this.kids = kids;}

}
