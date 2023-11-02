package edu.cmart.service;

import com.google.maps.model.DistanceMatrix;
import edu.cmart.model.dto.ServiceCarDto;
import edu.cmart.model.dto.TripDto;
import edu.cmart.model.request.DistanceRequest;
import org.springframework.stereotype.Service;

@Service
public interface TripService {

    TripDto getTripByServiceCarAndLatLng(
            ServiceCarDto serviceCarDto,
            DistanceMatrix distanceMatrix,
            DistanceRequest distanceRequest);
}
