package com.example.vehicle.service.impl;

import com.example.vehicle.dto.VehicleInfoDto;
import com.example.vehicle.repository.VehicleInfoRepository;
import com.example.vehicle.service.VehicleInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleInfoServiceImpl implements VehicleInfoService {
    private final VehicleInfoRepository repository;
//    private CacheManager cacheManager;

    @Override
    public VehicleInfoDto getByPlate(String plate) {
//        cacheManager.getCache("vehicles");
        List<VehicleInfoDto> vehicleInfos = Optional.of(
                        repository.findByPlateOrderByStartDate(plate).orElseThrow(null)
                                .stream()
                                .map(VehicleInfoDto::of)
                                .filter(Objects::nonNull).collect(Collectors.toList()))
                .filter(a -> !a.isEmpty())
                .orElseThrow(() -> new InvalidConfigurationPropertyValueException("Plate", plate, "Plate can not be found")
                );

        for (VehicleInfoDto dto : vehicleInfos) {
            if (dto.getState() == 1) {
                return dto;
            }
        }

        throw new InvalidConfigurationPropertyValueException("Plate", plate, "Plate can not be found");
    }
}
