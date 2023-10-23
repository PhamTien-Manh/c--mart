package edu.cmart.service;

import edu.cmart.model.dto.DistrictDto;
import edu.cmart.model.dto.SearchCriteria;
import edu.cmart.model.dto.WardDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface WardService {

    Page<WardDto> findAll(SearchCriteria searchCriteria);

    Page<WardDto> findAllByDistrictId(Long districtId, SearchCriteria searchCriteria);

    Page<WardDto> findAllByName(String name, SearchCriteria searchCriteria);

    WardDto findById(Long wardId);

    WardDto save(WardDto dto);

    void delete(Long wardId);
}
