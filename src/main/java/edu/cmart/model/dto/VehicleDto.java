package edu.cmart.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleDto {
    private Long id;
    private String licensePlate;
    private String name;
    private String description;
    private String image;
    private Long serviceCarId;
    private Long roleDriverId;
}
