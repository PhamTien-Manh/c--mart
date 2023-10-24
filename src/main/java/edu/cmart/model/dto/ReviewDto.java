package edu.cmart.model.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class ReviewDto {
    private Long id;
    private Double rate;
    private String comment;
    private Boolean isUpdated;
    private Date date;

    private Long tripId;
    private Long roleUserId;

}
