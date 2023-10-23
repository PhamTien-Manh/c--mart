package edu.cmart.facade;

import edu.cmart.exception.common.IdMustBeNullException;
import edu.cmart.exception.common.InvalidParamException;
import edu.cmart.exception.core.ArchitectureException;
import edu.cmart.exception.entity.EntityNotFoundException;
import edu.cmart.model.dto.SearchCriteria;
import edu.cmart.model.dto.ServiceCarDto;
import edu.cmart.service.ServiceCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceCarFacade {

    private final ServiceCarService serviceCarService;

    public Page<ServiceCarDto> findAll(SearchCriteria searchCriteria) throws ArchitectureException{
        Page<ServiceCarDto> dtos = serviceCarService.findAll(searchCriteria);
        if(dtos.isEmpty()){
            throw new EntityNotFoundException();
        }
        return dtos;
    }

    public Page<ServiceCarDto> findAllByTypeVehicleId(Long typeVehicleId, SearchCriteria searchCriteria) throws ArchitectureException{
        if(typeVehicleId == null){
            throw new InvalidParamException();
        }
        Page<ServiceCarDto> dtos = serviceCarService.findAllByTypeVehicleId(typeVehicleId, searchCriteria);
        if(dtos.isEmpty()){
            throw new EntityNotFoundException();
        }
        return dtos;
    }

    public List<ServiceCarDto> findAllByCityId(Long cityId) throws ArchitectureException{
        if(cityId == null){
            throw new InvalidParamException();
        }
        List<ServiceCarDto> dtos = serviceCarService.findAllByCityId(cityId);
        if(dtos.isEmpty()){
            throw new EntityNotFoundException();
        }
        return dtos;
    }

    public ServiceCarDto findById(Long serviceCarId) throws ArchitectureException{
        if(serviceCarId == null)
            throw new InvalidParamException();
        ServiceCarDto dto = serviceCarService.findById(serviceCarId);
        if(dto == null)
            throw new EntityNotFoundException();
        return dto;
    }

    public ServiceCarDto create(ServiceCarDto dto) throws ArchitectureException{
        if(dto == null)
            throw new InvalidParamException();
        if(dto.getId() != null)
            throw new IdMustBeNullException();
        return serviceCarService.save(dto);
    }

    public ServiceCarDto update(Long serviceCarId, ServiceCarDto dto) throws ArchitectureException{
        if(dto == null)
            throw new InvalidParamException();
        findById(serviceCarId);
        dto.setId(serviceCarId);
        return serviceCarService.save(dto);
    }

    public void isActived(Long serviceCarId, Boolean isActived) throws ArchitectureException{
        findById(serviceCarId);
        serviceCarService.isActived(serviceCarId, isActived);
    }
}
