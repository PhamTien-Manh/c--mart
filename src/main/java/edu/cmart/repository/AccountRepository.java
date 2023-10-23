package edu.cmart.repository;

import edu.cmart.entity.Account;
import edu.cmart.entity.enums.Gender;
import edu.cmart.entity.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Page<Account> findAllByRoleAccountsRole(Role role, Pageable pageable);

    Page<Account> findAllByRoleAccountsRoleAndIsActivated(Role role, Boolean isActivated, Pageable pageable);

    Page<Account> findAllByRoleAccountsRoleAndGender(Role role, Gender gender, Pageable pageable);

    Page<Account> findAllByRoleAccountsRoleAndFullnameContaining(Role role, String fullname, Pageable pageable);

    Page<Account> findAllByRoleAccountsRoleAndPhoneNumberContaining(Role role, String phoneNumber, Pageable pageable);

}