package edu.cmart.facade;

import edu.cmart.exception.common.IdMustBeNullException;
import edu.cmart.exception.common.InvalidParamException;
import edu.cmart.exception.core.ArchitectureException;
import edu.cmart.exception.entity.EntityNotFoundException;
import edu.cmart.model.dto.CityDto;
import edu.cmart.model.dto.SearchCriteria;
import edu.cmart.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityFacade {

    private final CityService cityService;

    public Page<CityDto> findAll(SearchCriteria searchCriteria) throws ArchitectureException {
        Page<CityDto> dtos = cityService.findAll(searchCriteria);
        if(dtos.isEmpty())
            throw new EntityNotFoundException();
        return dtos;
    }

    public Page<CityDto> findAllByName(String name, SearchCriteria searchCriteria) throws ArchitectureException {
        if (name == null)
            throw new InvalidParamException();
        Page<CityDto> dtos = cityService.findAllByName(name, searchCriteria);
        if(dtos.isEmpty())
            throw new EntityNotFoundException();
        return dtos;
    }

    public CityDto findById(Long cityId) throws ArchitectureException {
        if (cityId == null)
            throw new InvalidParamException();
        CityDto dto = cityService.findById(cityId);
        if(dto == null)
            throw new EntityNotFoundException();
        return dto;
    }

    public CityDto create(CityDto dto) throws ArchitectureException {
        if (dto == null)
            throw new InvalidParamException();
        if(dto.getId() != null)
            throw new IdMustBeNullException();
        return cityService.save(dto);
    }

    public CityDto update(Long cityId, CityDto dto) throws ArchitectureException {
        findById(cityId);
        if (dto == null)
            throw new InvalidParamException();
        dto.setId(cityId);
        return cityService.save(dto);
    }

    public void delete(Long cityId) throws ArchitectureException {
        findById(cityId);
        cityService.delete(cityId);
    }
}
