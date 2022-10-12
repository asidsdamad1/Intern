package com.example.vehicleinfo.repository;

import com.example.vehicleinfo.domain.VehicleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleInfoRepository extends JpaRepository<VehicleInfo, Long> {
    @Query("select v from VehicleInfo v where v.plate = ?1 order by v.startDate desc")
    List<VehicleInfo> findByPlateOrderByStartDate(String plate);

}
