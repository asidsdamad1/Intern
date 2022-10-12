package com.example.vehicleinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class VehicleInfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(VehicleInfoApplication.class, args);
    }

}
