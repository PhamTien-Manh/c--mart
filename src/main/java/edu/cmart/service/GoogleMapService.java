package edu.cmart.service;

import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.GeocodingResult;
import edu.cmart.model.request.DistanceRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface GoogleMapService {

    DistanceMatrix getDistance(DistanceRequest distanceRequest) throws IOException, InterruptedException, ApiException;

    GeocodingResult[] getCoordinates(String[] addresses) throws IOException, InterruptedException, ApiException;

    GeocodingResult[] getGeocode(DistanceRequest distanceRequest) throws IOException, InterruptedException, ApiException;
}
