package edu.cmart.service;

import edu.cmart.entity.Account;
import edu.cmart.entity.Role;
import edu.cmart.model.dto.AccountDto;
import edu.cmart.model.dto.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {

    Page<AccountDto> findAllByRole(String role, SearchCriteria searchCriteria);

    Page<AccountDto> findAllByRoleAndActivated(String role, Boolean isActivated, SearchCriteria searchCriteria);

    Page<AccountDto> findAllByRoleAndGender(String role, String gender, SearchCriteria searchCriteria);

    Page<AccountDto> findAllByRoleAndFullname(String role, String fullname, SearchCriteria searchCriteria);

    Page<AccountDto> findAllByRoleAndPhoneNumber(String role, String phoneNumber, SearchCriteria searchCriteria);

    AccountDto findById(Long accountId);

    AccountDto create(Account account, String role, List<Role> roles);

    AccountDto update(AccountDto accountDto, Account oldAccount);

    void setRole(Role role, String typeRole);

    UserDetailsService userDetailsService();
}
