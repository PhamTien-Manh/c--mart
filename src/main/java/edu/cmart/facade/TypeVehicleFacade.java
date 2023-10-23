package edu.cmart.facade;

import edu.cmart.exception.common.IdMustBeNullException;
import edu.cmart.exception.common.InvalidParamException;
import edu.cmart.exception.core.ArchitectureException;
import edu.cmart.exception.entity.EntityNotFoundException;
import edu.cmart.model.dto.TypeVehicleDto;
import edu.cmart.service.TypeVehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeVehicleFacade {

    private final TypeVehicleService typeVehicleService;

    public List<TypeVehicleDto> findAll() throws ArchitectureException{
        List<TypeVehicleDto> dtos = typeVehicleService.findAll();
        if(dtos.isEmpty())
            throw new EntityNotFoundException();
        return dtos;
    }

    public TypeVehicleDto findById(Long typeVehicleId) throws ArchitectureException{
        if (typeVehicleId == null)
            throw new InvalidParamException();
        TypeVehicleDto dto = typeVehicleService.findById(typeVehicleId);
        if(dto == null)
            throw new EntityNotFoundException();
        return dto;
    }

    public TypeVehicleDto create(TypeVehicleDto dto) throws ArchitectureException{
        if (dto == null)
            throw new InvalidParamException();
        if(dto.getId() != null)
            throw new IdMustBeNullException();
        return typeVehicleService.save(dto);
    }

    public TypeVehicleDto update(Long typeVehicleId, TypeVehicleDto dto) throws ArchitectureException{
        if (dto == null)
            throw new InvalidParamException();
        findById(typeVehicleId);
        dto.setId(typeVehicleId);
        return typeVehicleService.save(dto);
    }

    public void deleteById(Long typeVehicleId) throws ArchitectureException{
        findById(typeVehicleId);
        typeVehicleService.deleteById(typeVehicleId);
    }

}
