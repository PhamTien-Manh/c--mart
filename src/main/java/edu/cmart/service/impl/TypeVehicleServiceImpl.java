package edu.cmart.service.impl;

import edu.cmart.entity.TypeVehicle;
import edu.cmart.model.dto.TypeVehicleDto;
import edu.cmart.model.mapper.TypeVehicleMapper;
import edu.cmart.repository.TypeVehicleRepository;
import edu.cmart.service.TypeVehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TypeVehicleServiceImpl implements TypeVehicleService {

    private final TypeVehicleRepository typeVehicleRepository;
    private final TypeVehicleMapper typeVehicleMapper;

    @Override
    public List<TypeVehicleDto> findAll() {
        List<TypeVehicle> list = typeVehicleRepository.findAll();
        return list.stream().map(typeVehicleMapper::apply).toList();
    }

    @Override
    public TypeVehicleDto findById(Long typeVehicleId) {
        Optional<TypeVehicle> typeVehicle = typeVehicleRepository.findById(typeVehicleId);
        return typeVehicle.map(typeVehicleMapper::apply).orElse(null);
    }

    @Override
    public TypeVehicleDto save(TypeVehicleDto typeVehicleDto) {
        return typeVehicleMapper.apply(
                typeVehicleRepository.save(
                        typeVehicleMapper.applyToTypeVehicle(typeVehicleDto)));
    }

    @Override
    public void deleteById(Long typeVehicleId) {
        typeVehicleRepository.deleteById(typeVehicleId);
    }
}
