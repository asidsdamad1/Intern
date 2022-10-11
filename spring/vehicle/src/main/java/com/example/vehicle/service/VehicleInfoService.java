package com.example.vehicle.service;

import com.example.vehicle.dto.VehicleInfoDto;
import org.springframework.stereotype.Service;

@Service
public interface VehicleInfoService {
    VehicleInfoDto getByPlate(String plate);
}
