package edu.cmart.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;

import java.util.Date;

@AllArgsConstructor
@Builder
public class LatLngDriverRequest {

    @NonNull
    private Double latitude;
    @NonNull
    private Double longitude;
    @NonNull
    private Date updatedAt;
}
