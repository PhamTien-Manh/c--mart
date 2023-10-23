package edu.cmart.service;

import edu.cmart.model.dto.DistrictDto;
import edu.cmart.model.dto.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface DistrictService {

    Page<DistrictDto> findAll(SearchCriteria searchCriteria);

    Page<DistrictDto> findAllByCityId(Long cityId, SearchCriteria searchCriteria);

    Page<DistrictDto> findAllByName(String name, SearchCriteria searchCriteria);

    DistrictDto findById(Long districtId);

    DistrictDto save(DistrictDto dto);

    void delete(Long districtId);
}
