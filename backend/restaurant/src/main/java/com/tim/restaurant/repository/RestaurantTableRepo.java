package com.tim.restaurant.repository;

import com.tim.restaurant.model.RestaurantTable;
import com.tim.restaurant.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantTableRepo extends JpaRepository<RestaurantTable, Long> {
    List<RestaurantTable> findBySeatsGreaterThanEqual(int seats);
    List<RestaurantTable> findByZone(Zone zone);
    List<RestaurantTable> findByZoneAndSeatsGreaterThanEqual(Zone zone, int seats);
}
