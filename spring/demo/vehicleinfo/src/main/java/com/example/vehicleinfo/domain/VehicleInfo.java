package com.example.vehicleinfo.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicle_info")
public class VehicleInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "tag")
    private boolean tag;

    // 29A13234
    @Column(name = "plate")
    private String plate;

    // 29A-132.34
    @Column(name = "plate_face")
    private String plateFace;

    // 29A-132.34T
    @Column(name = "plate_full")
    private String plateFull;

    @Column(name = "state")
    private int state;

    @Column(name = "start_date")
    private Date startDate;

}
