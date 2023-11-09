package edu.cmart.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LocationDriver implements Serializable {
    private Long id;
    private Double lat;
    private Double lng;
    private Date time;
    private Boolean status;
    private Double distance;
    private Long serviceCarId;
}
