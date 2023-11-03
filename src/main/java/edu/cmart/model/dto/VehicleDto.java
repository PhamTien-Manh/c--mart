package edu.cmart.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class VehicleDto {
    private Long id;
    private String fullname;
    private String phone;
    private String imageDriver;
    private String licensePlate;
    private String name;
    private String description;
    private String image;
    private Double latitude;
    private Double longitude;
    private Date updatedAt;
    private Boolean isActived;
    private Long serviceCarId;
    private Long roleDriverId;
}
