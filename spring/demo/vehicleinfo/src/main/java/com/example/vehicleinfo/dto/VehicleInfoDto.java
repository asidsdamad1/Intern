package com.example.vehicleinfo.dto;

import com.example.vehicleinfo.common.Constants;
import com.example.vehicleinfo.domain.VehicleInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class VehicleInfoDto implements Serializable {
    private long id;
    private boolean tag;
    private String plate;
    private String plateFace;
    private String plateFull;
    private int state;
    private Date startDate;

    public static VehicleInfo toEntity(VehicleInfoDto dto) {
        return Constants.map().convertValue(dto, VehicleInfo.class);
    }

    public static VehicleInfoDto of(VehicleInfo  entity) {
        return Constants.map().convertValue(entity, VehicleInfoDto.class);
    }
}
