package edu.cmart.model.mapper;

import edu.cmart.entity.Account;
import edu.cmart.model.dto.AccountDto;
import edu.cmart.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class AccountMapper implements Function<Account, AccountDto> {

    private final AccountRepository accountRepository;

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

    public Account applyToAccount(AccountDto accountDto) {
        Account accountOld = accountRepository.findById(accountDto.getId()).get();
        return Account
                .builder()
                .id(accountDto.getId())
                .fullname(accountDto.getFullname())
                .email(accountDto.getEmail())
                .phoneNumber(accountDto.getPhoneNumber())
                .gender(accountDto.getGender())
                .image(accountDto.getImage())
                .isActivated(accountDto.getIsActivated())
                .birthday(accountDto.getBirthday())
                .password(accountOld.getPassword())
                .quickPassword(accountOld.getQuickPassword())
                .build();
    }


}
