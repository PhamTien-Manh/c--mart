package edu.cmart.model.dto;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

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
    private Long cityId;
    @NonNull
    private Long typeVehicleId;
}
