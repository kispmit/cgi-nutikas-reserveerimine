package com.tim.restaurant.controller;


import com.tim.restaurant.model.RestaurantTable;
import com.tim.restaurant.repository.RestaurantTableRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TableController {
    private final RestaurantTableRepo TableRepo;

    public TableController(RestaurantTableRepo TableRepo) {
        this.TableRepo = TableRepo;
    }

    @GetMapping("/api/tables")
    public List<RestaurantTable> getAllTables() {
        return TableRepo.findAll();
    }
}
