package edu.cmart.model.mapper;

import edu.cmart.entity.TypeVehicle;
import edu.cmart.model.dto.TypeVehicleDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TypeVehicleMapper implements Function<TypeVehicle, TypeVehicleDto> {


    @Override
    public TypeVehicleDto apply(TypeVehicle typeVehicle) {
        return TypeVehicleDto
                .builder()
                .id(typeVehicle.getId())
                .name(typeVehicle.getName())
                .description(typeVehicle.getDescription())
                .build();
    }

    public TypeVehicle applyToTypeVehicle(TypeVehicleDto typeVehicleDto){
        return TypeVehicle
                .builder()
                .id(typeVehicleDto.getId())
                .name(typeVehicleDto.getName())
                .description(typeVehicleDto.getDescription())
                .build();
    }
}
