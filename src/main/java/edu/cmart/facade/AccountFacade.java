package edu.cmart.facade;

import edu.cmart.exception.common.IdMustBeNullException;
import edu.cmart.exception.common.InvalidParamException;
import edu.cmart.exception.core.ArchitectureException;
import edu.cmart.exception.entity.EntityAlreadyExistException;
import edu.cmart.exception.entity.EntityNotFoundException;
import edu.cmart.model.dto.AccountDto;
import edu.cmart.model.dto.SearchCriteria;
import edu.cmart.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountFacade {

    private final AccountService accountService;

    public Page<AccountDto> findAllByRole(String role, SearchCriteria searchCriteria) throws ArchitectureException {
        if (role == null)
            throw new InvalidParamException();
        Page<AccountDto> dtos = accountService.findAllByRole(role, searchCriteria);
        if(dtos.isEmpty())
            throw new EntityNotFoundException();
        return dtos;
    }

    public Page<AccountDto> findAllByRoleAndActivated(String role, Boolean isActivated, SearchCriteria searchCriteria) throws ArchitectureException {
        if (role == null || isActivated == null)
            throw new InvalidParamException();
        Page<AccountDto> dtos = accountService.findAllByRoleAndActivated(role, isActivated, searchCriteria);
        if(dtos.isEmpty())
            throw new EntityNotFoundException();
        return dtos;
    }

    public Page<AccountDto> findAllByRoleAndGender(String role, String gender, SearchCriteria searchCriteria) throws ArchitectureException{
        if (role == null || gender == null)
            throw new InvalidParamException();
        Page<AccountDto> dtos = accountService.findAllByRoleAndGender(role, gender, searchCriteria);
        if(dtos.isEmpty())
            throw new EntityNotFoundException();
        return dtos;
    }

    public Page<AccountDto> findAllByRoleAndFullname(String role, String fullname, SearchCriteria searchCriteria) throws ArchitectureException{
        if (role == null || fullname == null)
            throw new InvalidParamException();
        Page<AccountDto> dtos = accountService.findAllByRoleAndFullname(role, fullname, searchCriteria);
        if(dtos.isEmpty())
            throw new EntityNotFoundException();
        return dtos;
    }

    public Page<AccountDto> findAllByRoleAndPhoneNumber(String role, String phoneNumber, SearchCriteria searchCriteria) throws ArchitectureException{
        if (role == null || phoneNumber == null)
            throw new InvalidParamException();
        Page<AccountDto> dtos = accountService.findAllByRoleAndPhoneNumber(role, phoneNumber, searchCriteria);
        if(dtos.isEmpty())
            throw new EntityNotFoundException();
        return dtos;
    }

    public AccountDto findById(Long id) throws ArchitectureException{
        if (id == null)
            throw new InvalidParamException();
        AccountDto dto = accountService.findById(id);
        if(dto == null)
            throw new EntityNotFoundException();
        return dto;
    }

    public AccountDto update(Long accountId, AccountDto accountDto) throws ArchitectureException{
        if (accountDto == null)
            throw new InvalidParamException();
        AccountDto accoutnOld = findById(accountId);

        return accountService.save(accountDto);
    }

    public AccountDto create(AccountDto accountDto) throws ArchitectureException{
        if (accountDto == null)
            throw new InvalidParamException();
        if (accountDto.getId() != null)
            throw new IdMustBeNullException();
        return accountService.save(accountDto);
    }

    public void isLock(Long accountId, Boolean isLock) throws ArchitectureException{
        if (accountId == null || isLock == null)
            throw new InvalidParamException();
        accountService.isLock(accountId, isLock);
    }

}
