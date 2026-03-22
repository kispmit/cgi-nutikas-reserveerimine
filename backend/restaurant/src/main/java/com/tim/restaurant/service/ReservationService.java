package com.tim.restaurant.service;

import com.tim.restaurant.dto.ReservationRequest;
import com.tim.restaurant.model.Reservation;
import com.tim.restaurant.model.RestaurantTable;
import com.tim.restaurant.repository.ReservationRepo;
import com.tim.restaurant.repository.RestaurantTableRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepo reservationRepo;
    private final RestaurantTableRepo tableRepo;

    public ReservationService(ReservationRepo reservationRepo, RestaurantTableRepo tableRepo) {
        this.reservationRepo = reservationRepo;
        this.tableRepo = tableRepo;
    }

    public Reservation createReservation(ReservationRequest request) {
        RestaurantTable table = tableRepo.findById(request.getTableId())
                .orElseThrow(() -> new RuntimeException("Lauda ei leitud."));

        if (request.getPeopleCount() > table.getSeats()) {
            throw new RuntimeException("Valitud laud on selle seltskonna jaoks liiga väike.");
        }

        List<Reservation> overlappingReservations =
                reservationRepo.findByTableAndStartTimeLessThanAndEndTimeGreaterThan(
                        table,
                        request.getEndTime(),
                        request.getStartTime()
                );

        if (!overlappingReservations.isEmpty()) {
            throw new RuntimeException("Valitud laud on sellel ajavahemikul juba hõivatud.");
        }

        Reservation reservation = new Reservation();
        reservation.setTable(table);
        reservation.setPeoplecount(request.getPeopleCount());
        reservation.setStartTime(request.getStartTime());
        reservation.setEndTime(request.getEndTime());

        return reservationRepo.save(reservation);
    }
}