package edu.cmart.repository;

import edu.cmart.entity.Ward;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WardRepository extends JpaRepository<Ward, Long> {

    Page<Ward> findAll(Pageable pageable);
    Page<Ward> findAllByDistrictId(Long districtId, Pageable pageable);
    Page<Ward> findAllByNameContaining(String name, Pageable pageable);


}