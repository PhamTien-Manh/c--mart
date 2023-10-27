package edu.cmart.model.request;

import lombok.*;

@Data
@AllArgsConstructor
@ToString
@Builder
public class DistanceRequest {
    @NonNull
    private Double firstLat;
    @NonNull
    private Double firstLng;
    @NonNull
    private Double secondLat;
    @NonNull
    private Double secondLng;
}
