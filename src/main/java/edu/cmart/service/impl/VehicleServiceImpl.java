package edu.cmart.service.impl;

import edu.cmart.entity.Vehicle;
import edu.cmart.model.dto.VehicleDto;
import edu.cmart.model.mapper.VehicleMapper;
import edu.cmart.repository.VehicleRepository;
import edu.cmart.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    @Override
    public VehicleDto findByRoleId(Long roleId) {
        return vehicleMapper.apply(vehicleRepository.findByRoleDriverId(roleId));
    }

    @Override
    public Vehicle findById(Long id) {
        return null;
    }
}
