package edu.cmart.service;

import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import edu.cmart.entity.Payment;
import edu.cmart.entity.Promo;
import edu.cmart.entity.Role;
import edu.cmart.exception.core.ArchitectureException;
import edu.cmart.model.dto.ServiceCarDto;
import edu.cmart.model.dto.TripDto;
import edu.cmart.model.request.DistanceRequest;
import edu.cmart.model.response.TripSaveResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface TripService {

    TripDto getTripByServiceCarAndLatLng(
            ServiceCarDto serviceCarDto,
            DistanceMatrix distanceMatrix,
            DistanceRequest distanceRequest);

    TripSaveResponse save(TripDto tripDto,
                          Payment payment,
                          Promo promo,
                          Role roleUser,
                          Long serviceCarId)
            throws ArchitectureException, IOException, InterruptedException, ApiException;
}
