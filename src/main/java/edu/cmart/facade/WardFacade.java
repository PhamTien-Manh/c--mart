package edu.cmart.facade;

import edu.cmart.exception.common.IdMustBeNullException;
import edu.cmart.exception.common.InvalidParamException;
import edu.cmart.exception.core.ArchitectureException;
import edu.cmart.exception.entity.EntityNotFoundException;
import edu.cmart.model.dto.SearchCriteria;
import edu.cmart.model.dto.WardDto;
import edu.cmart.service.WardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WardFacade {

    private final WardService wardService;

    public Page<WardDto> findAll(SearchCriteria searchCriteria) throws ArchitectureException {
        Page<WardDto> dtos = wardService.findAll(searchCriteria);
        if(dtos.isEmpty())
            throw new EntityNotFoundException();
        return dtos;
    }

    public Page<WardDto> findAllByDistrictId(Long districtId, SearchCriteria searchCriteria) throws ArchitectureException {
        if (districtId == null)
            throw new InvalidParamException();
        Page<WardDto> dtos = wardService.findAllByDistrictId(districtId, searchCriteria);
        if(dtos.isEmpty())
            throw new EntityNotFoundException();
        return dtos;
    }

    public Page<WardDto> findAllByName(String name, SearchCriteria searchCriteria) throws ArchitectureException {
        if (name == null)
            throw new InvalidParamException();
        Page<WardDto> dtos = wardService.findAllByName(name, searchCriteria);
        if(dtos.isEmpty())
            throw new EntityNotFoundException();
        return dtos;
    }

    public WardDto findById(Long wardId) throws ArchitectureException {
        if (wardId == null)
            throw new InvalidParamException();
        WardDto dto = wardService.findById(wardId);
        if(dto == null)
            throw new EntityNotFoundException();
        return dto;
    }

    public WardDto create(WardDto dto) throws ArchitectureException {
        if (dto == null)
            throw new InvalidParamException();
        if (dto.getId() != null)
            throw new IdMustBeNullException();
        return wardService.save(dto);
    }

    public WardDto update(Long wardId, WardDto dto) throws ArchitectureException {
        if (dto == null)
            throw new InvalidParamException();
        findById(wardId);
        dto.setId(wardId);
        return wardService.save(dto);
    }

    public void delete(Long wardId) throws ArchitectureException {
        findById(wardId);
        wardService.delete(wardId);
    }
}
