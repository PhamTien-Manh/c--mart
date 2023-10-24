package edu.cmart.service;

import edu.cmart.model.dto.SearchCriteria;
import edu.cmart.model.dto.ServiceCarDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface ServiceCarService {

    Page<ServiceCarDto> findAll(SearchCriteria searchCriteria);

//    List<ServiceCarDto> findAllByCityId(Long cityId);

    Page<ServiceCarDto> findAllByTypeVehicleId(Long typeVehicleId, SearchCriteria searchCriteria);

    ServiceCarDto findById(Long serviceCarId);

    ServiceCarDto save(ServiceCarDto dto);

    void isActived(Long serviceCarId, Boolean isActived);
}
