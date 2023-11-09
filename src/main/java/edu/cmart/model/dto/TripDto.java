package edu.cmart.model.dto;

import edu.cmart.entity.enums.TypeTrip;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor(force = true)
public class TripDto {
    private Long id;
    @NonNull
    private Double totalMoney;
    @NonNull
    private Double distance;
    private Date startTime;
    private Date finishTime;
    private Date receiveTime;
    @NonNull
    private Double time;
    @NonNull
    private Double startLat;
    @NonNull
    private Double startLng;
    @NonNull
    private Double finishLat;
    @NonNull
    private Double finishLng;
    private Boolean isScheduled;
    @NonNull
    private TypeTrip typeTrip;
    @NonNull
    private Long paymentId;
    private Long promoId;
    private Long roleDriverId;
    @NonNull
    private Long roleUserId;

}
