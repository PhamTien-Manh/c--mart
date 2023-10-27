package edu.cmart.facade;

import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.GeocodingResult;
import edu.cmart.exception.common.InvalidParamException;
import edu.cmart.exception.core.ArchitectureException;
import edu.cmart.exception.entity.EntityNotFoundException;
import edu.cmart.model.request.DistanceRequest;
import edu.cmart.service.GoogleMapService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class GoogleMapFacade {

    private final GoogleMapService googleMapService;

    public DistanceMatrix getDistance(DistanceRequest distanceRequest) throws ArchitectureException, IOException, InterruptedException, ApiException {
        if (distanceRequest == null) {
            throw new InvalidParamException();
        }
        DistanceMatrix distanceMatrix = googleMapService.getDistance(distanceRequest);
        if (distanceMatrix == null) {
            throw new EntityNotFoundException();
        }
        return distanceMatrix;
    }

    public GeocodingResult[] getCoordinates(String[] addresses) throws ArchitectureException, IOException, InterruptedException, ApiException {
        if (addresses.length < 2) {
            throw new InvalidParamException();
        }
        GeocodingResult[] geocodingResults = googleMapService.getCoordinates(addresses);
        if (geocodingResults == null) {
            throw new EntityNotFoundException();
        }
        return geocodingResults;
    }

    public GeocodingResult[] getGeocode(DistanceRequest distanceRequest) throws ArchitectureException, IOException, InterruptedException, ApiException {
        if (distanceRequest == null) {
            throw new InvalidParamException();
        }
        GeocodingResult[] geocodingResults = googleMapService.getGeocode(distanceRequest);
        if (geocodingResults == null) {
            throw new EntityNotFoundException();
        }
        return geocodingResults;
    }

}
