package com.example.vehicleinfo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VehicleinfoApplicationTests {

    @Test
    void contextLoads() {
        //  java  8
        /* vehicleInfos.orElseGet(Collections::emptyList).stream()
             .filter(veh  ->  veh.getState() ==  1).findFirst().map(VehicleInfoDto::of).orElseThrow();
             */

    }

}
