package com.example.vehicleinfo.service;

import com.example.vehicleinfo.dto.VehicleInfoDto;
import org.springframework.stereotype.Service;

@Service
public interface VehicleInfoService {
    VehicleInfoDto getByPlate(String plate, int typeCache);
}
