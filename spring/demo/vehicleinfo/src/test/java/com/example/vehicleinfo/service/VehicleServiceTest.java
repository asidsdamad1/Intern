package com.example.vehicleinfo.service;

import com.example.vehicleinfo.VehicleInfoApplication;
import com.example.vehicleinfo.domain.VehicleInfo;
import com.example.vehicleinfo.dto.VehicleInfoDto;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = VehicleInfoApplication.class)
class VehicleServiceTest {
    @Autowired
    private  VehicleInfoService service;
    @Autowired
    private RedisCacheManager cacheManager;

    @Test
    void  testCache() {
        Optional<VehicleInfoDto> dto = Optional.ofNullable(service.getByPlate("51A25569"));
        org.junit.jupiter.api.Assertions.assertEquals(dto, getCachedBook(dto.get().getPlate()));
    }

    private Optional<VehicleInfoDto> getCachedBook(String plate) {
        return ofNullable(cacheManager.getCache("vehicle")).map(c -> c.get(plate, VehicleInfoDto.class));
    }
}
