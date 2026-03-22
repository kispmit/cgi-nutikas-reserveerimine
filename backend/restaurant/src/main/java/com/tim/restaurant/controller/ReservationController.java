package com.tim.restaurant.controller;

import com.tim.restaurant.dto.ReservationRequest;
import com.tim.restaurant.model.Reservation;
import com.tim.restaurant.service.ReservationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public Reservation createReservation(@RequestBody ReservationRequest request) {
        return reservationService.createReservation(request);
    }
}