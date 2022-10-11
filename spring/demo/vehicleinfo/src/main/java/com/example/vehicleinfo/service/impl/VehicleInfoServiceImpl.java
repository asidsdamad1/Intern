package com.example.vehicleinfo.service.impl;

import com.example.vehicleinfo.common.Constants;
import com.example.vehicleinfo.dto.VehicleInfoDto;
import com.example.vehicleinfo.repository.VehicleInfoRepository;
import com.example.vehicleinfo.service.VehicleInfoService;
import com.example.vehicleinfo.utils.RedisUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleInfoServiceImpl implements VehicleInfoService {
    private final VehicleInfoRepository repository;
    private final RedisUtils redisUtils;
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public VehicleInfoDto getByPlate(String plate, int typeCache) {
        // find vehicle info in cache
        // null -> find in database
        if( Constants.REDIS_CACHE == typeCache && getByCache(plate) != null) {
            log.info("get vehicle info by cache");
            return getByCache(plate);
        }

        List<VehicleInfoDto> vehicleInfos = Optional.of(
                        repository.findByPlateOrderByStartDate(plate).orElseThrow(null)
                                .stream()
                                .map(VehicleInfoDto::of)
                                .filter(Objects::nonNull).collect(Collectors.toList()))
                .filter(a -> !a.isEmpty())
                .orElseThrow(() -> new InvalidConfigurationPropertyValueException("Plate", plate, "Plate can not be found")
                );

        log.info("get vehicle info by db");
        for (VehicleInfoDto dto : vehicleInfos) {
            if (dto.getState() == 1) {
                redisUtils.setValue(dto.getPlate(), dto);
                return dto;
            }
        }

        throw new InvalidConfigurationPropertyValueException("Plate", plate, "Plate can not be found");
    }

    public VehicleInfoDto getByCache(String plate) {
        try {
            String content = redisUtils.getValue(plate);
            if(content !=  null) {
                return Constants.map().readValue(content, VehicleInfoDto.class);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
