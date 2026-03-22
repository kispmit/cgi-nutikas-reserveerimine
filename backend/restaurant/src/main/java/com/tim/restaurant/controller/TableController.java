package com.tim.restaurant.controller;


import com.tim.restaurant.model.RestaurantTable;
import com.tim.restaurant.model.Zone;
import com.tim.restaurant.repository.RestaurantTableRepo;
import com.tim.restaurant.service.TableService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class TableController {

    private final RestaurantTableRepo TableRepo;
    private final TableService tableService;

    public TableController(RestaurantTableRepo tableRepo, TableService tableService) {
        this.TableRepo = tableRepo;
        this.tableService = tableService;
    }

    @GetMapping("/api/tables")
    public List<RestaurantTable> getAllTables() {
        return TableRepo.findAll();
    }

    @GetMapping("/api/tables/search")
    public List<RestaurantTable> searchTables(
            @RequestParam int peoplecount,
            @RequestParam(required = false) Zone zone,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime
    ) {
        return tableService.searchAvailableTables(peoplecount, zone, startTime, endTime);
    }
}
