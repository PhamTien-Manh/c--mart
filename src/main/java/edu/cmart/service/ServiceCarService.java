package edu.cmart.service;

import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import edu.cmart.entity.TypeVehicle;
import edu.cmart.model.dto.SearchCriteria;
import edu.cmart.model.dto.ServiceCarDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServiceCarService {

    Page<ServiceCarDto> findAll(SearchCriteria searchCriteria);

    List<ServiceCarDto> findAllByCityAndDistrict(GeocodingResult[] geocodingResults);

    Page<ServiceCarDto> findAllByTypeVehicleId(Long typeVehicleId, SearchCriteria searchCriteria);

    ServiceCarDto findById(Long serviceCarId);

    ServiceCarDto save(ServiceCarDto dto, TypeVehicle typeVehicle);

    List<ServiceCarDto> findAllByCityId(Long cityId);

    void isActived(Long serviceCarId, Boolean isActived);
}
