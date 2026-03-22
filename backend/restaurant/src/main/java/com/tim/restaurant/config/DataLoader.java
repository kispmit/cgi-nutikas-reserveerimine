package com.tim.restaurant.config;


import com.tim.restaurant.model.RestaurantTable;
import com.tim.restaurant.model.Zone;
import com.tim.restaurant.repository.ReservationRepo;
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
                restaurantTableRepo.save(new RestaurantTable(2, Zone.Main));
                restaurantTableRepo.save(new RestaurantTable(4, Zone.Main));
                restaurantTableRepo.save(new RestaurantTable(8, Zone.Main));
                restaurantTableRepo.save(new RestaurantTable(2, Zone.Terrace));
                restaurantTableRepo.save(new RestaurantTable(4, Zone.Terrace));
                restaurantTableRepo.save(new RestaurantTable(6, Zone.Private));
            }
        };
    }
}
