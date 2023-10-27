package edu.cmart.service.impl;

import edu.cmart.entity.Account;
import edu.cmart.entity.Role;
import edu.cmart.entity.enums.Gender;
import edu.cmart.entity.enums.TypeRoles;
import edu.cmart.model.dto.AccountDto;
import edu.cmart.model.dto.SearchCriteria;
import edu.cmart.model.mapper.AccountMapper;
import edu.cmart.repository.AccountRepository;
import edu.cmart.repository.RoleRepository;
import edu.cmart.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static edu.cmart.util.method.Search.getPageable;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final RoleRepository roleRepository;

    @Override
    public Page<AccountDto> findAllByRole(String role, SearchCriteria searchCriteria) {
        Page<Account> accounts = accountRepository.findAllByRolesTypeRoles(
                TypeRoles.valueOf(upperCase(role)),
                getPageable(searchCriteria));
        return accounts.map(accountMapper::apply);
    }

    @Override
    public Page<AccountDto> findAllByRoleAndActivated(String role, Boolean isActivated, SearchCriteria searchCriteria) {
        Page<Account> accounts = accountRepository.findAllByRolesTypeRolesAndIsActivated(
                TypeRoles.valueOf(upperCase(role)),
                isActivated,
                getPageable(searchCriteria));
        return accounts.map(accountMapper::apply);
    }

    @Override
    public Page<AccountDto> findAllByRoleAndGender(String role, String gender, SearchCriteria searchCriteria) {
        Page<Account> accounts = accountRepository.findAllByRolesTypeRolesAndGender(
                TypeRoles.valueOf(upperCase(role)),
                Gender.valueOf(upperCase(gender)),
                getPageable(searchCriteria));
        return accounts.map(accountMapper::apply);
    }

    @Override
    public Page<AccountDto> findAllByRoleAndFullname(String role, String fullname, SearchCriteria searchCriteria) {
        Page<Account> accounts = accountRepository.findAllByRolesTypeRolesAndFullnameContaining(
                TypeRoles.valueOf(upperCase(role)),
                fullname,
                getPageable(searchCriteria));
        return accounts.map(accountMapper::apply);
    }

    @Override
    public Page<AccountDto> findAllByRoleAndPhoneNumber(String role, String phoneNumber, SearchCriteria searchCriteria) {
        Page<Account> accounts = accountRepository.findAllByRolesTypeRolesAndPhoneNumberContaining(
                TypeRoles.valueOf(upperCase(role)),
                phoneNumber,
                getPageable(searchCriteria));
        return accounts.map(accountMapper::apply);
    }

    @Override
    public AccountDto findById(Long accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        return account.map(accountMapper::apply).orElse(null);
    }

    @Override
    public AccountDto create(Account account, String role, List<Role> roles) {
        Account accountNew;
        if(!roles.isEmpty()){
            accountNew = roleRepository.save(new Role(TypeRoles.valueOf(role), roles.get(0).getAccount())).getAccount();
        }
        else {
            accountNew = accountRepository.save(account);
            roleRepository.save(new Role(TypeRoles.valueOf(role), accountNew));
        }
        return accountMapper.apply(accountNew);

    }

    @Override
    public AccountDto update(AccountDto accountDto, Account oldAccount) {
        return accountMapper.apply(accountRepository.save(accountMapper.applyToAccount(accountDto, oldAccount)));
    }

    @Override
    public void setRole(Role role, String typeRole) {
        role.setTypeRoles(TypeRoles.valueOf(typeRole));
        roleRepository.save(role);
    }

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String phoneNumber) {
                return accountRepository.findByPhoneNumber(phoneNumber)
                        .orElseThrow(() -> new UsernameNotFoundException("Account not found"));
            }
        };
    }

    public String upperCase(String name){
        return name.toUpperCase();
    }
}
