package com.example.vehicle;

import com.example.vehicle.domain.VehicleInfo;
import com.example.vehicle.repository.VehicleInfoRepository;
import com.example.vehicle.utils.DateUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class VehicleApplication {

    public static void main(String[] args) {
        SpringApplication.run(VehicleApplication.class, args);
    }

//    @Bean
//    CommandLineRunner run(VehicleInfoRepository repository) {
//        return args -> {
//            VehicleInfo vehicle1 = VehicleInfo.builder()
//                    .tag(true)
//                    .plate("90B20567")
//                    .plateFace("90B-205.67")
//                    .plateFull("90B-205.67T")
//                    .state(0)
//                    .startDate(DateUtils.formatToDate("2022.02.18 04:14:04 +00:00"))
//                    .build();
//
//            VehicleInfo vehicle2 = VehicleInfo.builder()
//                    .tag(false)
//                    .plate("79H12345")
//                    .plateFace("79H-123.45")
//                    .plateFull("79H-123.45X")
//                    .state(1)
//                    .startDate(DateUtils.formatToDate("2022.08.18 07:20:24 +00:00"))
//                    .build();
//
//            VehicleInfo vehicle3 = VehicleInfo.builder()
//                    .tag(true)
//                    .plate("50F00001")
//                    .plateFace("50F-000.01")
//                    .plateFull("50F-000.01V")
//                    .state(1)
//                    .startDate(DateUtils.formatToDate("2022.09.10 12:05:00 +00:00"))
//                    .build();
//
//            VehicleInfo vehicle4 = VehicleInfo.builder()
//                    .tag(false)
//                    .plate("29C99999")
//                    .plateFace("29C-999.99")
//                    .plateFull("29C-999.99X")
//                    .state(0)
//                    .startDate(DateUtils.formatToDate("2022.02.07 13:05:00 +00:00"))
//                    .build();
//
//            VehicleInfo vehicle5 = VehicleInfo.builder()
//                    .tag(true)
//                    .plate("51A25569")
//                    .plateFace("51A-255.69")
//                    .plateFull("51A-255.69T")
//                    .state(0)
//                    .startDate(DateUtils.formatToDate("2022.09.25 17:05:00 +00:00"))
//                    .build();
//
//            VehicleInfo vehicle6 = VehicleInfo.builder()
//                    .tag(true)
//                    .plate("51A25569")
//                    .plateFace("51A-255.69")
//                    .plateFull("51A-255.69T")
//                    .state(1)
//                    .startDate(DateUtils.formatToDate("2022.09.26 17:05:00 +00:00"))
//                    .build();
//
//            repository.save(vehicle1);
//            repository.save(vehicle2);
//            repository.save(vehicle3);
//            repository.save(vehicle4);
//            repository.save(vehicle5);
//            repository.save(vehicle6);
//        };
//    }
}
