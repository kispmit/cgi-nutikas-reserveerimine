package com.tim.restaurant.repository;

import com.tim.restaurant.model.Reservation;
import com.tim.restaurant.model.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    List<Reservation> findByTable(RestaurantTable table);

    List<Reservation> findByTableAndStartTimeLessThanAndEndTimeGreaterThan(RestaurantTable table, Date startTime, Date endTime);

}
