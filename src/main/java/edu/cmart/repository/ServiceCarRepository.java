package edu.cmart.repository;

import edu.cmart.entity.ServiceCar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceCarRepository extends JpaRepository<ServiceCar, Long> {

    Page<ServiceCar> findAll(Pageable pageable);
    @Query("""
            SELECT sc FROM ServiceCar sc 
            INNER JOIN ServiceOfCity soc ON sc.id = soc.serviceCar.id
            INNER JOIN City c ON c.id = soc.city.id 
            INNER JOIN District d ON c.id = d.city.id
            WHERE c.name LIKE concat('%', ?1, '%')
            AND d.name LIKE concat('%', ?2, '%')
            """)
    List<ServiceCar> findAllByCityAndDistrict(String city, String district);

    List<ServiceCar> findAllByServiceOfCitiesCityId(Long cityId);
    Page<ServiceCar> findAllByTypeVehicleId(Long typeVehicleId, Pageable pageable);
}