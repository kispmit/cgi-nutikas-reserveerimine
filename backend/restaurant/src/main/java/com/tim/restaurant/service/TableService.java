package com.tim.restaurant.service;


import com.tim.restaurant.dto.RecommendationRequest;
import com.tim.restaurant.dto.RecommendationResponse;
import com.tim.restaurant.model.Reservation;
import com.tim.restaurant.model.RestaurantTable;
import com.tim.restaurant.model.Zone;
import com.tim.restaurant.repository.ReservationRepo;
import com.tim.restaurant.repository.RestaurantTableRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
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
    public RecommendationResponse recommendTable(RecommendationRequest request) {
        List<RestaurantTable> availableTables = searchAvailableTables(request.getPeopleCount(), request.getZone(), request.getStartTime(), request.getEndTime());

        if (availableTables.isEmpty()) {
            return new RecommendationResponse(null, List.of(), List.of(), "Sobivaid vabu laudu ei leitud valitud ajal ja tsoonis.");
        }
        int targetSeats = availableTables.stream().map(RestaurantTable::getSeats).filter(seats -> seats >= request.getPeopleCount()).min(Integer::compareTo).orElseThrow();

        List<RestaurantTable> sizeMatchedTables = availableTables.stream().filter(table -> table.getSeats() == targetSeats).toList();

        List<String> requestedPreferences = new ArrayList<>();
        if (request.isQuiet()) requestedPreferences.add("Vaikne nurk");
        if (request.isWindow()) requestedPreferences.add("Akna all");
        if (request.isKids()) requestedPreferences.add("Laste mängunurga lähedal");

        List<RestaurantTable> sortedTables = sizeMatchedTables.stream().sorted(Comparator.comparingInt(table -> -calculatePreference(table, request))).toList();

        RestaurantTable bestTable = sortedTables.get(0);

        List<String> unmetPreferences = getUnmetPreferences(bestTable, request);

        String message;

        if (requestedPreferences.isEmpty()) {
            message = "Leidsime parima saadaval oleva laua valitud tsoonis.";
        } else if (unmetPreferences.isEmpty()) {
            message = "Leidsime laua, mis vastab soovitud tsoonile, istekohtadele ja kõikidele eelistustele.";
        } else {
            message = "Kõiki eelistusi ei saa pakkuda. Soovitame lähimat võimalikku varianti.";
        }

        List<RestaurantTable> alternatives = sortedTables.stream().skip(1).limit(3).toList();

        return new RecommendationResponse(bestTable, alternatives, unmetPreferences, message);

    }

    private int calculatePreference(RestaurantTable table, RecommendationRequest request) {
        int score = 0;

        if (request.isQuiet() && table.isQuiet()) score++;
        if (request.isWindow() && table.isWindowSeat()) score++;
        if (request.isKids() && table.isKids()) score++;

        return score;
    }
    private List<String> getUnmetPreferences(RestaurantTable table, RecommendationRequest request) {
        List<String> unmet = new ArrayList<>();

        if (request.isQuiet() && !table.isQuiet()) {
            unmet.add("Vaikne nurk");
        }
        if (request.isWindow() && !table.isWindowSeat()) {
            unmet.add("Akna all");
        }
        if (request.isKids() && !table.isKids()) {
            unmet.add("Laste mängunurga lähedal");
        }

        return unmet;
    }


}
