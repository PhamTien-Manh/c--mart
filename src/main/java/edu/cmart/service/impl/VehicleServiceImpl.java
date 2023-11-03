package edu.cmart.service.impl;

import edu.cmart.entity.Vehicle;
import edu.cmart.model.request.LatLngDriverRequest;
import edu.cmart.repository.VehicleRepository;
import edu.cmart.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;


    @Override
    public void setLatLng(Long id, LatLngDriverRequest latLngDriverRequest) {
//        vehicleRepository.setLatLng(id, latLngDriverRequest);
    }

    @Override
    public Vehicle findById(Long id) {
        return null;
    }
}
