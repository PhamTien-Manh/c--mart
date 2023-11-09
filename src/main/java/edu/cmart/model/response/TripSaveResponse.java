package edu.cmart.model.response;

import edu.cmart.model.dto.DriverDto;
import edu.cmart.model.dto.TripDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TripSaveResponse {

    private TripDto tripDto;
    private DriverDto driverDto;
}
