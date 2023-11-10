package edu.cmart.service;

import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import edu.cmart.exception.core.ArchitectureException;
import edu.cmart.model.dto.DriverDto;
import edu.cmart.model.dto.LocationDriver;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface DriverService {

    List<LocationDriver> getDriverMostRecent(Long serviceCarId, LatLng latLng);

    DriverDto getDriverAccept(List<LocationDriver> locationDrivers, LatLng latLng)
            throws ArchitectureException, IOException, InterruptedException, ApiException;
}
