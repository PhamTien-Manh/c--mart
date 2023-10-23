package edu.cmart.service.impl;

import edu.cmart.entity.ServiceCar;
import edu.cmart.model.dto.SearchCriteria;
import edu.cmart.model.dto.ServiceCarDto;
import edu.cmart.model.mapper.ServiceCarMapper;
import edu.cmart.repository.ServiceCarRepository;
import edu.cmart.service.ServiceCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static edu.cmart.util.method.Search.getPageable;

@Service
@RequiredArgsConstructor
public class ServiceCarServiceImpl implements ServiceCarService {

    private final ServiceCarRepository serviceCarRepository;
    private final ServiceCarMapper serviceCarMapper;

    @Override
    public Page<ServiceCarDto> findAll(SearchCriteria searchCriteria) {
        Page<ServiceCar> serviceCars = serviceCarRepository.findAll(getPageable(searchCriteria));
        return serviceCars.map(serviceCarMapper::apply);
    }

    @Override
    public List<ServiceCarDto> findAllByCityId(Long cityId) {
        List<ServiceCar> serviceCars = serviceCarRepository.findAllByCityId(cityId);
        return serviceCars.stream().map(serviceCarMapper::apply).toList();
    }

    @Override
    public Page<ServiceCarDto> findAllByTypeVehicleId(Long typeVehicleId, SearchCriteria searchCriteria) {
        Page<ServiceCar> serviceCars = serviceCarRepository.findAllByTypeVehicleId(typeVehicleId, getPageable(searchCriteria));
        return serviceCars.map(serviceCarMapper::apply);
    }

    @Override
    public ServiceCarDto findById(Long serviceCarId) {
        Optional<ServiceCar> serviceCar = serviceCarRepository.findById(serviceCarId);
        return serviceCar.map(serviceCarMapper::apply).orElse(null);
    }

    @Override
    public ServiceCarDto save(ServiceCarDto dto) {
        return serviceCarMapper.apply(serviceCarRepository.save(serviceCarMapper.applyToServiceCar(dto)));
    }

    @Override
    public void isActived(Long serviceCarId, Boolean isActived) {
        Optional<ServiceCar> serviceCar = serviceCarRepository.findById(serviceCarId);
        if(serviceCar.isPresent()){
            serviceCar.get().setIsActived(isActived);
            serviceCarRepository.save(serviceCar.get());
        }
    }
}
