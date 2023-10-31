package edu.cmart.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@AllArgsConstructor
@Builder
@Data
public class ServiceCarDto {

    private Long id;
    @NonNull
    private Double fixedPrice;
    @NonNull
    private Double priceFluctuates;
    @NonNull
    private Double timeFluctuates;
    @NonNull
    private Double fixedKm;
    @NonNull
    private Boolean isActived;
    @NonNull
    private Long typeVehicleId;
    private String typeVehicleName;
}
