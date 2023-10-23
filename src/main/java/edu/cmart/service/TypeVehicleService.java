package edu.cmart.service;

import edu.cmart.model.dto.TypeVehicleDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TypeVehicleService {

    List<TypeVehicleDto> findAll();

    TypeVehicleDto findById(Long typeVehicleId);

    TypeVehicleDto save(TypeVehicleDto typeVehicleDto);

    void deleteById(Long typeVehicleId);
}
