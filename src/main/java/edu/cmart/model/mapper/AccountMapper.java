package edu.cmart.model.mapper;

import edu.cmart.entity.Account;
import edu.cmart.model.dto.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class AccountMapper implements Function<Account, AccountDto> {

    @Override
    public AccountDto apply(Account account) {
        return AccountDto
                .builder()
                .id(account.getId())
                .fullname(account.getFullname())
                .email(account.getEmail())
                .phoneNumber(account.getPhoneNumber())
                .gender(account.getGender())
                .image(account.getImage())
                .isActivated(account.getIsActivated())
                .birthday(account.getBirthday())
                .build();
    }

    public Account applyToAccount(AccountDto accountDto, Account oldAccount) {
        return Account
                .builder()
                .id(accountDto.getId())
                .fullname(accountDto.getFullname())
                .email(oldAccount.getEmail())
                .phoneNumber(oldAccount.getPhoneNumber())
                .gender(accountDto.getGender())
                .image(accountDto.getImage())
                .isActivated(oldAccount.getIsActivated())
                .birthday(accountDto.getBirthday())
                .password(oldAccount.getPassword())
                .quickPassword(oldAccount.getQuickPassword())
                .build();
    }




}
