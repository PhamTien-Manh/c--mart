package edu.cmart.service.impl;


import edu.cmart.entity.Account;
import edu.cmart.entity.Role;
import edu.cmart.entity.enums.TypeRoles;
import edu.cmart.model.request.LoginRequest;
import edu.cmart.model.request.RegisterRequest;
import edu.cmart.model.response.JwtAuthenticationResponse;
import edu.cmart.repository.AccountRepository;
import edu.cmart.repository.RoleRepository;
import edu.cmart.service.AuthenticationService;
import edu.cmart.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static edu.cmart.util.method.RoleValueIndex.getIndex;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    /*
    *  Phương thức signup() nhận vào thông tin đăng ký của người dùng và trả về
    *  Sẽ chuyển thông tin đăng ký của người dùng thành đối tượng User và lưu vào cơ sở dữ liệu.
    *  Sau đó, sẽ tạo ra một chuỗi JWT và trả về cho người dùng.
    * */
        @Override
        public JwtAuthenticationResponse register(RegisterRequest request) {
            // Chuyển đổi thông tin
            Account account = Account
                    .builder()
                    .phoneNumber(request.getPhoneNumber())
                    .fullname(request.getFullName())
                    .gender(request.getGender())
                    .birthday(request.getBirthday())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .quickPassword(passwordEncoder.encode(request.getQuickPassword()))
                    .isActivated(true)
                    .build();
            Account accountNew = accountRepository.save(account);
            // Tạo role
            // Lưu role
            Set<Role> roles = new HashSet<>();
            roles.add(
                    roleRepository.save(
                            new Role(TypeRoles.USER, accountNew)));
            // Tạo chuỗi JWT và role sẽ được chuyển thành một list số
            var jwt = jwtService.generateToken(accountNew, getIndex(roles));
            return JwtAuthenticationResponse.builder().token(jwt).build();
        }


    /*
    *  Phương thức signin() nhận vào thông tin đăng nhập của người dùng và trả về
    *
    * */
    @Override
    public JwtAuthenticationResponse login(LoginRequest request) {
        // đoạn này sẽ kiểm tra thông tin đăng nhập của người dùng
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getPhoneNumber(), request.getPassword()));
        Account account = accountRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow(() -> new IllegalArgumentException("Invalid phone number or password."));
        Set<Role> role = roleRepository.findByAccountId(account.getId());
        // Tạo chuỗi JWT
        var jwt = jwtService.generateToken(account, getIndex(role));
        return JwtAuthenticationResponse.apply(account, jwt);
    }
}
