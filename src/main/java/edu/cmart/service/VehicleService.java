package edu.cmart.service;

import edu.cmart.entity.Vehicle;
import edu.cmart.model.dto.VehicleDto;
import org.springframework.stereotype.Service;

@Service
public interface VehicleService {

//    @Query("UPDATE Vehicle SET latitude = :latitude, longitude = :longitude, updatedAt = :updatedAt WHERE id = :id")

    VehicleDto findByRoleId(Long roleId);
    Vehicle findById(Long id);
}
