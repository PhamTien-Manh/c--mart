package edu.cmart.service.impl;

import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.LatLng;
import edu.cmart.entity.Role;
import edu.cmart.entity.enums.TypeRoles;
import edu.cmart.exception.core.ArchitectureException;
import edu.cmart.facade.GoogleMapFacade;
import edu.cmart.model.dto.*;
import edu.cmart.model.mapper.AccountMapper;
import edu.cmart.model.mapper.RoleMapper;
import edu.cmart.model.request.DistanceRequest;
import edu.cmart.repository.RoleRepository;
import edu.cmart.service.DriverService;
import edu.cmart.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final CacheManager cacheManager;
    private final GoogleMapFacade googleMapFacade;
    private final RoleRepository roleRepository;
    private final AccountMapper accountMapper;
    private final RoleMapper roleMapper;
    private final VehicleService vehicleService;

    @Override
    public List<LocationDriver> getDriverMostRecent(Long serviceCarId, LatLng latLng) {
        // Lấy danh sách tài xế thuộc service
        Cache serviceCarCache = cacheManager.getCache("services");
        List<LocationDriver> locationDrivers = serviceCarCache.get(serviceCarId, List.class);
        List<LocationDriver> listMostRecent = new ArrayList<>();
        // Kiểm tra tài xế nào đủ điều kiện để đưa vào list kiểm tra khoảng cách thực tế
        for (LocationDriver locationDriver : locationDrivers) {
            if (locationDriver.getStatus() == true
//                    System.currentTimeMillis() - locationDriver.getTime().getTime() <= 3 * 60 * 1000
            ) {
                locationDriver.setDistance(
                        Math.sqrt(
                        Math.pow(locationDriver.getLat() - latLng.lat, 2) +
                        Math.pow(locationDriver.getLng() - latLng.lng, 2)));
                listMostRecent.add(locationDriver);
            }
        }
        listMostRecent.sort(Comparator.comparingDouble(LocationDriver::getDistance));

        return listMostRecent.subList(0, Math.min(5, listMostRecent.size()));
    }

    @Override
    public DriverDto getDriverAccept(List<LocationDriver> locationDrivers, LatLng latLng)
            throws ArchitectureException, IOException, InterruptedException, ApiException {

        LocationDriver closestDriver = getClosestDriver(locationDrivers, latLng);

//      Lọc qua danh sách tài xế và tìm tài xế gần nhất

        List<Role> roles = roleRepository.findByAccountId(closestDriver.getId());
        Role roleDriver = null;
        for (Role role : roles) {
            if (role.getTypeRoles().equals(TypeRoles.DRIVER_ACTIVE)) {
                roleDriver = role;
            }
        }
        AccountDto accountDto = accountMapper.apply(roleDriver.getAccount());
        VehicleDto vehicleDto = vehicleService.findByRoleId(roleDriver.getId());
        RoleDto roleDto = roleMapper.apply(roleDriver);

        return new DriverDto(accountDto, roleDto, vehicleDto);
    }

    public LocationDriver getClosestDriver(List<LocationDriver> locationDrivers, LatLng latLng)
            throws ArchitectureException, IOException, InterruptedException, ApiException {

        LocationDriver closestDriver = null;
        Long shortestMeters = Long.MAX_VALUE;
        Long shortestSeconds = Long.MAX_VALUE;

        for (LocationDriver locationDriver : locationDrivers) {
            DistanceMatrix distanceMatrix = googleMapFacade
                    .getDistance(new DistanceRequest(
                            locationDriver.getLat(),
                            locationDriver.getLng(),
                            latLng.lat,
                            latLng.lng));
//      Lấy khoảng cách và thời gian chạy từ tài xế đến khách hàng
            Long meters = distanceMatrix.rows[0].elements[0].distance.inMeters;
            Long seconds = distanceMatrix.rows[0].elements[0].duration.inSeconds;

            if (meters < shortestMeters && seconds < shortestSeconds) {
                shortestMeters = meters;
                shortestSeconds = seconds;
                closestDriver = locationDriver;
            }
        }
        return closestDriver;
    }
}
