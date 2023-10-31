package edu.cmart.repository;

import edu.cmart.entity.Account;
import edu.cmart.entity.enums.Gender;
import edu.cmart.entity.enums.TypeRoles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Page<Account> findAllByRolesTypeRoles(TypeRoles typeRoles, Pageable pageable);

    Page<Account> findAllByRolesTypeRolesAndIsActivated(TypeRoles typeRoles, Boolean isActivated, Pageable pageable);

    Page<Account> findAllByRolesTypeRolesAndGender(TypeRoles typeRoles, Gender gender, Pageable pageable);

    @Query("""
            select a from Account a inner join a.roles roles
            where roles.typeRoles = ?1 and a.fullname like concat('%', ?2, '%')""")
    Page<Account> findAllByRolesTypeRolesAndFullnameContaining(TypeRoles typeRoles, String fullname, Pageable pageable);

    Page<Account> findAllByRolesTypeRolesAndPhoneNumberContaining(TypeRoles typeRoles, String phoneNumber, Pageable pageable);

    Optional<Account> findByPhoneNumber(String phoneNumber);

}