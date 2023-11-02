package edu.cmart.model.request;

import lombok.*;

@Data
@AllArgsConstructor
@ToString
@Builder
public class DistanceRequest {
    @NonNull
    private Double startLat;
    @NonNull
    private Double startLng;
    @NonNull
    private Double finishLat;
    @NonNull
    private Double finishLng;
}
