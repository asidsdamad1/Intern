package com.example.vehicleinfo.rest;

import com.example.vehicleinfo.common.Constants;
import com.example.vehicleinfo.dto.VehicleInfoDto;
import com.example.vehicleinfo.service.VehicleInfoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vehicle")
public class RestVehicleInfoController {

    private final VehicleInfoService service;

    @GetMapping
    public ResponseEntity<VehicleInfoDto> getVehicleByPlate(@RequestParam String plate) {
        return ResponseEntity.ok(service.getByPlate(plate, Constants.REDIS_CACHE));
    }

}
