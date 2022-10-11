package com.example.vehicle.repository;

import com.example.vehicle.domain.VehicleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleInfoRepository extends JpaRepository<VehicleInfo, Long> {
    @Query("select v from VehicleInfo v where v.plate = ?1 order by v.startDate")
    Optional<List<VehicleInfo>> findByPlateOrderByStartDate(String plate);

}
