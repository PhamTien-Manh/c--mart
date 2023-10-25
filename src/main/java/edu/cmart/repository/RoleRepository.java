package edu.cmart.repository;

import edu.cmart.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Set<Role> findByAccountId(Long accountId);

    List<Role> findAllByAccountPhoneNumber(String phoneNumber);
}