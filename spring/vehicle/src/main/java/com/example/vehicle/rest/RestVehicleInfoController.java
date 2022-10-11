package com.example.vehicle.rest;

import com.example.vehicle.dto.VehicleInfoDto;
import com.example.vehicle.service.VehicleInfoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vehicle")
public class RestVehicleInfoController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final VehicleInfoService service;

    @Cacheable(value = "vehicles", key = "#plate")
    @GetMapping
    public ResponseEntity<VehicleInfoDto> getVehicleByPlate(@RequestParam String plate) {
        LOG.info("Getting user with ID {}.", plate);

        return ResponseEntity.ok(service.getByPlate(plate));
    }

}
