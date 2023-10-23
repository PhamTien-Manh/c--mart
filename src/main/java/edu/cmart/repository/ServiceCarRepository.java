package edu.cmart.repository;

import edu.cmart.entity.ServiceCar;
import edu.cmart.model.dto.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceCarRepository extends JpaRepository<ServiceCar, Long> {

    Page<ServiceCar> findAll(Pageable pageable);

    List<ServiceCar> findAllByCityId(Long cityId);

    Page<ServiceCar> findAllByTypeVehicleId(Long typeVehicleId, Pageable pageable);
}