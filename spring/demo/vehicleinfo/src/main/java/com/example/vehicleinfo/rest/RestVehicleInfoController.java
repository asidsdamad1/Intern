package com.example.vehicleinfo.rest;

import com.example.vehicleinfo.common.Constants;
import com.example.vehicleinfo.dto.VehicleInfoDto;
import com.example.vehicleinfo.service.VehicleInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vehicle")
public class RestVehicleInfoController {

    private final VehicleInfoService service;

    @GetMapping
    public ResponseEntity<VehicleInfoDto> getVehicleByPlate(@RequestParam String plate) {
        return ResponseEntity.ok(service.getByPlate(plate));
    }

    @PostMapping
    public ResponseEntity<VehicleInfoDto> save(@RequestBody VehicleInfoDto  dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @GetMapping("/cache")
    public ResponseEntity<VehicleInfoDto> getCache(@RequestParam String plate) {
        return ResponseEntity.ok(service.getCache(plate));
    }
}
