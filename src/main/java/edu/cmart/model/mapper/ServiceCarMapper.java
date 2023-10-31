package edu.cmart.model.mapper;

import edu.cmart.entity.City;
import edu.cmart.entity.ServiceCar;
import edu.cmart.entity.TypeVehicle;
import edu.cmart.model.dto.ServiceCarDto;
import edu.cmart.repository.CityRepository;
import edu.cmart.repository.TypeVehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class ServiceCarMapper implements Function<ServiceCar, ServiceCarDto> {

    private final TypeVehicleRepository typeVehicleRepository;

    @Override
    public ServiceCarDto apply(ServiceCar serviceCar) {
        return ServiceCarDto
                .builder()
                .id(serviceCar.getId())
                .fixedPrice(serviceCar.getFixedPrice())
                .priceFluctuates(serviceCar.getPriceFluctuates())
                .timeFluctuates(serviceCar.getTimeFluctuates())
                .fixedKm(serviceCar.getFixedKm())
                .isActived(serviceCar.getIsActived())
                .typeVehicleId(serviceCar.getTypeVehicle().getId())
                .typeVehicleName(serviceCar.getTypeVehicle().getName())
                .build();
    }

    public ServiceCar applyToServiceCar(ServiceCarDto serviceCarDto, TypeVehicle typeVehicle) {
        return ServiceCar
                .builder()
                .id(serviceCarDto.getId())
                .fixedPrice(serviceCarDto.getFixedPrice())
                .priceFluctuates(serviceCarDto.getPriceFluctuates())
                .timeFluctuates(serviceCarDto.getTimeFluctuates())
                .fixedKm(serviceCarDto.getFixedKm())
                .isActived(serviceCarDto.getIsActived())
                .typeVehicle(typeVehicle)
                .build();
    }
}
