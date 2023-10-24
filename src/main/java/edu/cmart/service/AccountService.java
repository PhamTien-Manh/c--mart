package edu.cmart.service;

import edu.cmart.model.dto.AccountDto;
import edu.cmart.model.dto.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {

    Page<AccountDto> findAllByRole(String role, SearchCriteria searchCriteria);

    Page<AccountDto> findAllByRoleAndActivated(String role, Boolean isActivated, SearchCriteria searchCriteria);

    Page<AccountDto> findAllByRoleAndGender(String role, String gender, SearchCriteria searchCriteria);

    Page<AccountDto> findAllByRoleAndFullname(String role, String fullname, SearchCriteria searchCriteria);

    Page<AccountDto> findAllByRoleAndPhoneNumber(String role, String phoneNumber, SearchCriteria searchCriteria);

    AccountDto findById(Long accountId);

    AccountDto save(AccountDto accountDto);

    void isLock(Long accountId, Boolean isLock);

    UserDetailsService userDetailsService();
}
