package edu.cmart.facade;

import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import edu.cmart.exception.core.ArchitectureException;
import edu.cmart.exception.entity.EntityNotFoundException;
import edu.cmart.model.dto.DriverDto;
import edu.cmart.model.dto.LocationDriver;
import edu.cmart.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DriverFacade {

    private final DriverService driverService;

    public List<LocationDriver> getDriverMostRecent(Long serviceCarId, LatLng latLng) throws ArchitectureException {
        List<LocationDriver> list = driverService.getDriverMostRecent(serviceCarId, latLng);
        if (list.isEmpty()) throw new EntityNotFoundException();
        return list;
    }

    public DriverDto getDriverAccept(Long serviceCarId, LatLng latLng)
            throws ArchitectureException, IOException, InterruptedException, ApiException {

        DriverDto driverDto = driverService
                .getDriverAccept(
                        driverService.getDriverMostRecent(serviceCarId, latLng)
                        , latLng);

        if (driverDto == null) throw new EntityNotFoundException();
        return driverDto;
    }





}
