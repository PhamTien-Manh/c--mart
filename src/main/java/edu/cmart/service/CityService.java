package edu.cmart.service;

import edu.cmart.model.dto.CityDto;
import edu.cmart.model.dto.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface CityService {

    Page<CityDto> findAll(SearchCriteria searchCriteria);

    Page<CityDto> findAllByName(String name, SearchCriteria searchCriteria);

    CityDto findById(Long cityId);

    CityDto save(CityDto dto);

    void delete(Long cityId);
}
