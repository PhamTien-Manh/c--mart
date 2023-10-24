package edu.cmart.facade;

import edu.cmart.exception.common.IdMustBeNullException;
import edu.cmart.exception.common.InvalidParamException;
import edu.cmart.exception.core.ArchitectureException;
import edu.cmart.exception.entity.EntityNotFoundException;
import edu.cmart.model.dto.DistrictDto;
import edu.cmart.model.dto.SearchCriteria;
import edu.cmart.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DistrictFacade {

    private final DistrictService districtService;

    public Page<DistrictDto> findAll(SearchCriteria searchCriteria) throws ArchitectureException {
        Page<DistrictDto> dtos = districtService.findAll(searchCriteria);
        if(dtos.isEmpty())
            throw new EntityNotFoundException();
        return dtos;
    }

    public Page<DistrictDto> findAllByCityId(Long cityId, SearchCriteria searchCriteria) throws ArchitectureException {
        if (cityId == null)
            throw new InvalidParamException();
        Page<DistrictDto> dtos = districtService.findAllByCityId(cityId, searchCriteria);
        if(dtos.isEmpty())
            throw new EntityNotFoundException();
        return dtos;
    }

    public Page<DistrictDto> findAllByName(String name, SearchCriteria searchCriteria) throws ArchitectureException {
        if (name == null)
            throw new InvalidParamException();
        Page<DistrictDto> dtos = districtService.findAllByName(name, searchCriteria);
        if(dtos.isEmpty())
            throw new EntityNotFoundException();
        return dtos;
    }

    public DistrictDto findById(Long districtId) throws ArchitectureException {
        if (districtId == null)
            throw new InvalidParamException();
        DistrictDto dto = districtService.findById(districtId);
        if(dto == null)
            throw new EntityNotFoundException();
        return dto;
    }

    public DistrictDto create(DistrictDto dto) throws ArchitectureException {
        if (dto == null)
            throw new InvalidParamException();
        if(dto.getId() != null)
            throw new IdMustBeNullException();
        return districtService.save(dto);
    }

    public DistrictDto update(Long districtId, DistrictDto dto) throws ArchitectureException {
        if (dto == null)
            throw new InvalidParamException();
        findById(districtId);
        dto.setId(districtId);
        return districtService.save(dto);
    }

    public void delete(Long districtId) throws ArchitectureException {
        findById(districtId);
        districtService.delete(districtId);
    }

}
