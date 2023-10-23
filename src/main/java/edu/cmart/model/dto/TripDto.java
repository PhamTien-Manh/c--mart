package edu.cmart.model.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TripDto {
    private Long id;
    private Double totalMoney;
    private Double distance;
    private Date startTime;
    private Date finishTime;
    private Date receiveTime;
    private Date minTime;
    private String startLocation;
    private String finishLocation;
    private Boolean isScheduled;
    private Boolean isFinished;

    private Long paymentId;
    private Long promoId;
    private Long roleDriverId;
    private Long roleUserId;

}
