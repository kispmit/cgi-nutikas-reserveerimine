package com.tim.restaurant.config;

import com.tim.restaurant.model.RestaurantTable;
import com.tim.restaurant.model.Zone;
import com.tim.restaurant.repository.RestaurantTableRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(RestaurantTableRepo restaurantTableRepo) {
        return args -> {
            if (restaurantTableRepo.count() == 0) {
                restaurantTableRepo.save(new RestaurantTable(2, Zone.Main, true, false, false));
                restaurantTableRepo.save(new RestaurantTable(4, Zone.Main, false, true, false));
                restaurantTableRepo.save(new RestaurantTable(8, Zone.Main, false, false, true));
                restaurantTableRepo.save(new RestaurantTable(2, Zone.Terrace, true, true, false));
                restaurantTableRepo.save(new RestaurantTable(4, Zone.Terrace, false, true, true));
                restaurantTableRepo.save(new RestaurantTable(6, Zone.Private, true, false, false));
            }
        };
    }
}