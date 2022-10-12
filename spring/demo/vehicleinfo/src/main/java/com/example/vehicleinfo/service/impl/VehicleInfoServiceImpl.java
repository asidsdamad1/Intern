package com.example.vehicleinfo.service.impl;

import com.example.vehicleinfo.common.Constants;
import com.example.vehicleinfo.domain.VehicleInfo;
import com.example.vehicleinfo.dto.VehicleInfoDto;
import com.example.vehicleinfo.repository.VehicleInfoRepository;
import com.example.vehicleinfo.service.VehicleInfoService;
import com.example.vehicleinfo.utils.RedisUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleInfoServiceImpl implements VehicleInfoService {
    private final VehicleInfoRepository repository;
    private final RedisUtils redisUtils;

    @Override
    public VehicleInfoDto save(VehicleInfoDto dto) {
        if (dto != null) {
            VehicleInfo entity = VehicleInfo.builder()
                    .plate(dto.getPlate())
                    .plateFace(dto.getPlateFace())
                    .plateFull(dto.getPlateFull())
                    .startDate(dto.getStartDate())
                    .state(dto.getState())
                    .tag(dto.isTag())
                    .build();

            repository.save(entity);

            return VehicleInfoDto.of(entity);
        }
        return null;
    }

    public List<VehicleInfoDto> findAllByPlate(String plate) {
        return repository.findByPlateOrderByStartDate(plate)
                .orElseThrow(() -> new InvalidConfigurationPropertyValueException("Plate", plate, "Plate can not be found"))
                .stream()
                .map(VehicleInfoDto::of)
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(cacheNames = "vehicle", key = "#plate")
    public VehicleInfoDto getByPlate(String plate) {
        List<VehicleInfoDto> dtoList = findAllByPlate(plate);
        for (VehicleInfoDto dto : dtoList) {
            if (dto.getState() == 1) {
                return dto;
            }
        }
        throw new InvalidConfigurationPropertyValueException("Plate", plate, "Plate can not be found");
    }

    @Override
    public VehicleInfoDto getCache(String plate) {
        try {
            String content = redisUtils.getValue(plate);
            if (content != null) {
                return Constants.map().readValue(content, VehicleInfoDto.class);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
