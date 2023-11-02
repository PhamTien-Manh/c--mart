package edu.cmart.repository;

import edu.cmart.entity.Promo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromoRepository extends JpaRepository<Promo, Long> {

    Page<Promo> findAll(Pageable pageable);
    Page<Promo> findAllByNameContains(String name, Pageable pageable);
}