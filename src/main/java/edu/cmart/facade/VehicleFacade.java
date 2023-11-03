package edu.cmart.facade;

import edu.cmart.model.request.LatLngDriverRequest;
import edu.cmart.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleFacade {

    private final VehicleService vehicleService;

    public void setLatLng(Long id, LatLngDriverRequest latLngDriverRequest) {
        System.out.println();
    }
}
