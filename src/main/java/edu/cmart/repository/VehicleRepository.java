package edu.cmart.repository;

import edu.cmart.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

//    @Query("UPDATE Vehicle SET latitude = :latitude, longitude = :longitude, updatedAt = :updatedAt WHERE id = :id")
//    void setLatLng(Long id, LatLngDriverRequest latLngDriverRequest);

    Vehicle findByRoleDriverId(Long roleId);
}