package edu.cmart.service.impl;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import edu.cmart.model.request.DistanceRequest;
import edu.cmart.service.GoogleMapService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class GoogleMapServiceImpl implements GoogleMapService {

    private final GeoApiContext geoApiContext;

    @Override
    public DistanceMatrix getDistance(DistanceRequest distanceRequest) throws IOException, InterruptedException, ApiException {
        LatLng origin = new LatLng(distanceRequest.getStartLat(), distanceRequest.getStartLng());
        LatLng destination = new LatLng(distanceRequest.getFinishLat(), distanceRequest.getFinishLng());
        return DistanceMatrixApi
                .newRequest(geoApiContext)
                .origins(origin)
                .destinations(destination)
                .mode(TravelMode.DRIVING)
                .await();
    }

    @Override
    public GeocodingResult[] getCoordinates(String[] addresses) throws IOException, InterruptedException, ApiException {
        GeocodingResult[] results = new GeocodingResult[addresses.length];

        for (int i = 0; i < addresses.length; i++) {
            results[i] = GeocodingApi.geocode(geoApiContext, addresses[i]).await()[0];
        }

        return results;
    }

    @Override
    public GeocodingResult[] getAddress(LatLng latLng) throws IOException, InterruptedException, ApiException {
        return GeocodingApi.reverseGeocode(geoApiContext, latLng).await();
    }
}
