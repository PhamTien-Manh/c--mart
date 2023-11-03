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
import org.springframework.stereotype.Service;

import java.util.List;

import static edu.cmart.util.method.RoleValueIndex.getIndex;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AccountRepository accountRepository;
    private final JwtService jwtService;
    private final RoleRepository roleRepository;
    private final EncodePassAndMapperImpl encodePassAndMapper;

    /*
     *  Phương thức signup() nhận vào thông tin đăng ký của người dùng và trả về
     *  Sẽ chuyển thông tin đăng ký của người dùng thành đối tượng User và lưu vào cơ sở dữ liệu.
     *  Sau đó, sẽ tạo ra một chuỗi JWT và trả về cho người dùng.
     * */
    @Override
    public JwtAuthenticationResponse register(RegisterRequest request, List<Role> roles) {
        // Lấy account tùy biến theo roles
        List<Role> rolesNew = getRole(request, roles);
        Account account = rolesNew.get(0).getAccount();

        var accessToken = jwtService.generateAccessToken(account, getIndex(rolesNew));
        var refreshToken = jwtService.generateRefreshToken(account, getIndex(rolesNew));
        return JwtAuthenticationResponse.apply(account, accessToken, refreshToken);
    }

    /*
     *  Phương thức signin() nhận vào thông tin đăng nhập của người dùng và trả về
     *
     * */
    @Override
    public JwtAuthenticationResponse login(LoginRequest request, List<Role> roles) {
        Account account = roles.get(0).getAccount();
        // Tạo chuỗi JWT
        var accessToken = jwtService.generateAccessToken(account, getIndex(roles));
        var refreshToken = jwtService.generateRefreshToken(account, getIndex(roles));
        return JwtAuthenticationResponse.apply(account, accessToken, refreshToken);
    }

    @Override
    public String getAccessToken(Account account, List<Role> roles) {
        return jwtService.generateAccessToken(account, getIndex(roles));
    }


    public List<Role> getRole(RegisterRequest request, List<Role> roles) {
        // Lấy account để lưu role
        Account account = getAccount(request, roles);
        // Lưu role mới với account được tùy biến theo roles
        Role role = roleRepository.save(new Role(TypeRoles.USER_ACTIVE, account));
        roles.add(role);
        return roles;
    }

    public Account getAccount(RegisterRequest request, List<Role> roles) {
        Account account;
        // Nếu có role thì set lại account cũ
        if (!roles.isEmpty()) {
            account = roles.get(0).getAccount();
        }
        // Nếu chưa thì tạo mới và lưu để lấy id
        else {
            Account newAccount = encodePassAndMapper.encodePassAndMapper(request);

            account = accountRepository.save(newAccount);
        }
        return account;
    }


}
