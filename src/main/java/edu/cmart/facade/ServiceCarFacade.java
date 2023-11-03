package edu.cmart.facade;

import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import edu.cmart.entity.TypeVehicle;
import edu.cmart.exception.common.ForeignKeyIsNotFound;
import edu.cmart.exception.common.IdMustBeNullException;
import edu.cmart.exception.common.InvalidParamException;
import edu.cmart.exception.core.ArchitectureException;
import edu.cmart.exception.entity.EntityNotFoundException;
import edu.cmart.model.dto.SearchCriteria;
import edu.cmart.model.dto.ServiceCarDto;
import edu.cmart.repository.TypeVehicleRepository;
import edu.cmart.service.ServiceCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceCarFacade {

    private final ServiceCarService serviceCarService;
    private final GoogleMapFacade googleMapFacade;
    private final TypeVehicleRepository typeVehicleRepository;

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

    public List<ServiceCarDto> findAllByCityAndDistrict(LatLng latLng) throws ArchitectureException, IOException, InterruptedException, ApiException {
        GeocodingResult[] geocodingResults = googleMapFacade.getAddress(latLng);
        List<ServiceCarDto> dtos = serviceCarService.findAllByCityAndDistrict(geocodingResults);
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
        return serviceCarService.save(dto, getTypeVehicle(dto).get());
    }

    private Optional<TypeVehicle> getTypeVehicle(ServiceCarDto dto) throws ForeignKeyIsNotFound {
        Optional<TypeVehicle> typeVehicle = typeVehicleRepository.findById(dto.getTypeVehicleId());
        if(typeVehicle.isEmpty())
            throw new ForeignKeyIsNotFound(TypeVehicle.class.getSimpleName());
        return typeVehicle;
    }

    public ServiceCarDto update(Long serviceCarId, ServiceCarDto dto) throws ArchitectureException{
        if(dto == null)
            throw new InvalidParamException();
        findById(serviceCarId);
        dto.setId(serviceCarId);
        return serviceCarService.save(dto, getTypeVehicle(dto).get());
    }

    public void isActived(Long serviceCarId, Boolean isActived) throws ArchitectureException{
        if(isActived == null)
            throw new InvalidParamException();
        findById(serviceCarId);
        serviceCarService.isActived(serviceCarId, isActived);
    }

    public List<ServiceCarDto> findAllByCityId(Long cityId) throws ArchitectureException{
        if(cityId == null)
            throw new InvalidParamException();
        List<ServiceCarDto> dtos = serviceCarService.findAllByCityId(cityId);
        if(dtos.isEmpty())
            throw new EntityNotFoundException();
        return dtos;
    }
}
