package com.tim.restaurant.service;


import com.tim.restaurant.model.Reservation;
import com.tim.restaurant.model.RestaurantTable;
import com.tim.restaurant.model.Zone;
import com.tim.restaurant.repository.ReservationRepo;
import com.tim.restaurant.repository.RestaurantTableRepo;
import jakarta.persistence.Table;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TableService {

    private final RestaurantTableRepo tableRepo;
    private final ReservationRepo reservationRepo;

    public TableService(RestaurantTableRepo tableRepo, ReservationRepo reservationRepo) {
        this.tableRepo = tableRepo;
        this.reservationRepo = reservationRepo;
    }

    public List<RestaurantTable> searchAvailableTables(int peoplecount, Zone zone, LocalDateTime startTime, LocalDateTime endTime ) {
        List<RestaurantTable> canidateTables;
        if (zone != null) {
            canidateTables = tableRepo.findByZoneAndSeatsGreaterThanEqual(zone, peoplecount);
        } else {
            canidateTables = tableRepo.findBySeatsGreaterThanEqual(peoplecount);
        }
        List<RestaurantTable> availableTables = new ArrayList<>();

        for (RestaurantTable table : canidateTables) {
            List<Reservation> overlappingReservations =
                    reservationRepo.findByTableAndStartTimeLessThanAndEndTimeGreaterThan(
                            table, startTime, endTime
                    );
            if (overlappingReservations.isEmpty()) {
                availableTables.add(table);
            }
        }
        return availableTables;
    }
}
