package edu.cmart.repository;

import edu.cmart.entity.Ewallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EwalletRepository extends JpaRepository<Ewallet, Long> {
}