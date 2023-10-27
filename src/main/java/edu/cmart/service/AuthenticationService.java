package edu.cmart.service;


import edu.cmart.entity.Role;
import edu.cmart.model.request.LoginRequest;
import edu.cmart.model.request.RegisterRequest;
import edu.cmart.model.response.JwtAuthenticationResponse;

import java.util.List;

// Lớp này sẽ trả về token cho người dùng sau khi đăng ký hoặc đăng nhập thành công
public interface AuthenticationService {
    JwtAuthenticationResponse register(RegisterRequest request, List<Role> roles);

    JwtAuthenticationResponse login(LoginRequest request, List<Role> roles);
}
