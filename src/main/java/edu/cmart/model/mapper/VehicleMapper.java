package edu.cmart.model.mapper;

import edu.cmart.entity.Role;
import edu.cmart.entity.ServiceCar;
import edu.cmart.entity.Vehicle;
import edu.cmart.model.dto.VehicleDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class VehicleMapper implements Function<Vehicle, VehicleDto> {
    @Override
    public VehicleDto apply(Vehicle vehicle) {
        return VehicleDto
                .builder()
                .id(vehicle.getId())
                .fullname(vehicle.getRoleDriver().getAccount().getFullname())
                .phone(vehicle.getRoleDriver().getAccount().getPhoneNumber())
                .imageDriver(vehicle.getRoleDriver().getAccount().getImage())
                .licensePlate(vehicle.getLicensePlate())
                .name(vehicle.getName())
                .description(vehicle.getDescription())
                .image(vehicle.getImage())
                .latitude(vehicle.getLatitude())
                .longitude(vehicle.getLongitude())
                .updatedAt(vehicle.getUpdatedAt())
                .isActived(vehicle.getIsActived())
                .serviceCarId(vehicle.getServiceCar().getId())
                .roleDriverId(vehicle.getRoleDriver().getId())
                .build();
    }

    public Vehicle applyToVehicle(VehicleDto vehicleDto, ServiceCar serviceCar, Role roleDriver) {
        return Vehicle
                .builder()
                .id(vehicleDto.getId())
                .licensePlate(vehicleDto.getLicensePlate())
                .name(vehicleDto.getName())
                .description(vehicleDto.getDescription())
                .image(vehicleDto.getImage())
                .isActived(vehicleDto.getIsActived())
                .serviceCar(serviceCar)
                .roleDriver(roleDriver)
                .build();
    }
}
