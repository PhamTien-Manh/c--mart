package edu.cmart.repository;

import edu.cmart.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    Page<City> findAll(Pageable pageable);

    Page<City> findAllByNameContaining(String name, Pageable pageable);
}