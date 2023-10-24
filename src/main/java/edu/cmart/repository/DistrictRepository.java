package edu.cmart.repository;

import edu.cmart.entity.District;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

    Page<District> findAllByNameContaining(String name, Pageable pageable);

    Page<District> findAllByCityId(Long cityId, Pageable pageable);

    Page<District> findAll(Pageable pageable);
}