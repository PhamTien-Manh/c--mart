package edu.cmart.facade;

import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import edu.cmart.exception.core.ArchitectureException;
import edu.cmart.model.dto.ServiceCarDto;
import edu.cmart.model.dto.TripDto;
import edu.cmart.model.request.DistanceRequest;
import edu.cmart.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class TripFacade {

    private final TripService tripService;
    private final ServiceCarFacade serviceCarFacade;
    private final GoogleMapFacade googleMapFacade;

    public TripDto getTripByServiceCarAndLatLng(
            DistanceRequest distanceRequest,
            Long serviceCarId
    ) throws ArchitectureException, IOException, InterruptedException, ApiException
    {
        ServiceCarDto serviceCarDto = serviceCarFacade.findById(serviceCarId);
        DistanceMatrix distanceMatrix = googleMapFacade.getDistance(distanceRequest);

        TripDto trip = tripService.getTripByServiceCarAndLatLng(
                serviceCarDto,
                distanceMatrix,
                distanceRequest);
        if (trip == null) {
            throw new ArchitectureException();
        }
        return trip;
    }
}
