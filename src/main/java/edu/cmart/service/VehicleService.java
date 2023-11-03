package edu.cmart.service;

import edu.cmart.entity.Vehicle;
import edu.cmart.model.request.LatLngDriverRequest;
import org.springframework.stereotype.Service;

@Service
public interface VehicleService {

//    @Query("UPDATE Vehicle SET latitude = :latitude, longitude = :longitude, updatedAt = :updatedAt WHERE id = :id")
    void setLatLng(Long id, LatLngDriverRequest latLngDriverRequest);

    Vehicle findById(Long id);
}
