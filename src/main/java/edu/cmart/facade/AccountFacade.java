package edu.cmart.facade;

import edu.cmart.entity.Account;
import edu.cmart.entity.Role;
import edu.cmart.entity.enums.TypeRoles;
import edu.cmart.exception.common.InvalidParamException;
import edu.cmart.exception.core.ArchitectureException;
import edu.cmart.exception.entity.EntityNotFoundException;
import edu.cmart.model.dto.AccountDto;
import edu.cmart.model.dto.SearchCriteria;
import edu.cmart.model.request.RegisterRequest;
import edu.cmart.repository.AccountRepository;
import edu.cmart.service.AccountService;
import edu.cmart.service.EncodePassAndMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AccountFacade {

    private final AccountService accountService;
    private final RoleFacade roleFacade;
    private final AccountRepository accountRepository;
    private final EncodePassAndMapper encodePassAndMapper;


    public Page<AccountDto> findAllByRole(String role, SearchCriteria searchCriteria) throws ArchitectureException {
        if (role == null)
            throw new InvalidParamException();
        Page<AccountDto> dtos = accountService.findAllByRole(role, searchCriteria);
        if (dtos.isEmpty())
            throw new EntityNotFoundException();
        return dtos;
    }

    public Page<AccountDto> findAllByRoleAndActivated(String role, Boolean isActivated, SearchCriteria searchCriteria) throws ArchitectureException {
        if (role == null || isActivated == null)
            throw new InvalidParamException();
        Page<AccountDto> dtos = accountService.findAllByRoleAndActivated(role, isActivated, searchCriteria);
        if (dtos.isEmpty())
            throw new EntityNotFoundException();
        return dtos;
    }

    public Page<AccountDto> findAllByRoleAndGender(String role, String gender, SearchCriteria searchCriteria) throws ArchitectureException {
        if (role == null || gender == null)
            throw new InvalidParamException();
        Page<AccountDto> dtos = accountService.findAllByRoleAndGender(role, gender, searchCriteria);
        if (dtos.isEmpty())
            throw new EntityNotFoundException();
        return dtos;
    }

    public Page<AccountDto> findAllByRoleAndFullname(String role, String fullname, SearchCriteria searchCriteria) throws ArchitectureException {
        if (role == null || fullname == null)
            throw new InvalidParamException();
        Page<AccountDto> dtos = accountService.findAllByRoleAndFullname(role, fullname, searchCriteria);
        if (dtos.isEmpty())
            throw new EntityNotFoundException();
        return dtos;
    }

    public Page<AccountDto> findAllByRoleAndPhoneNumber(String role, String phoneNumber, SearchCriteria searchCriteria) throws ArchitectureException {
        if (role == null || phoneNumber == null)
            throw new InvalidParamException();
        Page<AccountDto> dtos = accountService.findAllByRoleAndPhoneNumber(role, phoneNumber, searchCriteria);
        if (dtos.isEmpty())
            throw new EntityNotFoundException();
        return dtos;
    }

    public AccountDto findById(Long id) throws ArchitectureException {
        if (id == null)
            throw new InvalidParamException();
        AccountDto dto = accountService.findById(id);
        if (dto == null)
            throw new EntityNotFoundException();
        return dto;
    }

    // Update thông tin cá nhân trừ thông tin nhạy cảm password, email, role, isActivated, phoneNumber
    public AccountDto update(Long accountId, AccountDto accountDto) throws ArchitectureException {
        if (accountDto == null || accountId == null)
            throw new InvalidParamException();
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isEmpty())
            throw new EntityNotFoundException();
        accountDto.setId(accountId);
        return accountService.update(accountDto, account.get());
    }

    // Update số điện thoại
    public AccountDto changeProfile(Long accountId, AccountDto accountDto) throws ArchitectureException {
        if (accountDto == null || accountId == null)
            throw new InvalidParamException();
        List<Role> roles = roleFacade.hasOtherRole(accountId);
        accountDto.setId(accountId);
        return accountService.update(accountDto, roles.get(0).getAccount());
    }

    // Create staff-driver cần cccd
    public AccountDto create(RegisterRequest request, String role) throws ArchitectureException {
        List<Role> roles = roleFacade.checkRole(request.getPhoneNumber(), role);
        if (request == null || role.isEmpty())
            throw new InvalidParamException();
        Account account = encodePassAndMapper.encodePassAndMapper(request);
        return accountService.create(account, role, roles);
    }

    public void setRole(Long accountId, String typeRole) throws ArchitectureException {
        if (accountId == null || typeRole.isEmpty())
            throw new InvalidParamException();
        Role role = roleFacade.getRole(accountId, typeRole);
        accountService.setRole(role, typeRole);
    }

}
