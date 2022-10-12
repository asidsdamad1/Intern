package com.example.vehicleinfo.configuration;


import com.example.vehicleinfo.VehicleInfoApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This integration Test requires following external dependencies to run:
 * 1.Redis
 */
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = VehicleInfoApplication.class)
class RedisCacheConfigurationITest {



    @Test
    void redisCacheConfigurationPropertiesTest() {

    }

}