package edu.cmart.service.impl;

import edu.cmart.entity.Account;
import edu.cmart.model.request.RegisterRequest;
import edu.cmart.service.EncodePassAndMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EncodePassAndMapperImpl implements EncodePassAndMapper {
    private final PasswordEncoder passwordEncoder;
    @Override
    public Account encodePassAndMapper(RegisterRequest request) {
        return Account
                .builder()
                .phoneNumber(request.getPhoneNumber())
                .fullname(request.getFullname())
                .gender(request.getGender())
                .birthday(request.getBirthday())
                .password(passwordEncoder.encode(request.getPassword()))
                .quickPassword(passwordEncoder.encode(request.getQuickPassword()))
                .build();
    }
}
