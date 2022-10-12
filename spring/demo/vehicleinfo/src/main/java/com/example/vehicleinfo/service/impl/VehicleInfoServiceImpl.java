package com.example.vehicleinfo.service.impl;

import com.example.vehicleinfo.cache.CacheManager;
import com.example.vehicleinfo.common.Constants;
import com.example.vehicleinfo.domain.VehicleInfo;
import com.example.vehicleinfo.dto.VehicleInfoDto;
import com.example.vehicleinfo.repository.VehicleInfoRepository;
import com.example.vehicleinfo.service.VehicleInfoService;
import com.example.vehicleinfo.utils.DateUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class VehicleInfoServiceImpl implements VehicleInfoService {
    private final VehicleInfoRepository repository;
    private final CacheManager cacheManager;
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public VehicleInfoDto save(VehicleInfoDto dto) {
        if (dto != null) {
            VehicleInfo entity = VehicleInfo.builder()
                    .plate(dto.getPlate())
                    .plateFace(dto.getPlateFace())
                    .plateFull(dto.getPlateFull())
                    .startDate(DateUtils.formatToDate(dto.getStartDate().toString()))
                    .state(dto.getState())
                    .tag(dto.isTag())
                    .build();

            repository.save(entity);

            return VehicleInfoDto.of(entity);
        }
        return null;
    }


    @Override
    public VehicleInfoDto getByPlate(String plate) {
        // check cache
        if (getByCache(plate) != null) {
            log.info("get vehicle info by cache");
            return getByCache(plate);
        }
        List<VehicleInfo> vehicleInfos = repository.findByPlateOrderByStartDate(plate);

        log.info("get vehicle info by db");
        // get vehicle state = 1 and newest date
        //  java  8
        /* vehicleInfos.orElseGet(Collections::emptyList).stream()
             .filter(veh  ->  veh.getState() ==  1).findFirst().map(VehicleInfoDto::of).orElseThrow();
             */

        // java > 9
        VehicleInfoDto result = Stream.ofNullable(vehicleInfos)
                .flatMap(Collection::stream)
                .filter(veh -> veh.getState() == 1).findFirst().map(VehicleInfoDto::of).orElseThrow();

        if (cacheManager.cacheUtils() != null) {
            cacheManager.cacheUtils().setValue(plate, result);
        }

        return result;

    }

    // get value in cache
    public VehicleInfoDto getByCache(String plate) {
        try {
            if (cacheManager.cacheUtils() != null) {
                String content = cacheManager.cacheUtils().getValue(plate);
                if (content != null) {
                    return Constants.map().readValue(content, VehicleInfoDto.class);
                }
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
