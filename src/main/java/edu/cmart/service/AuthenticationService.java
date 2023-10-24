package edu.cmart.service;


import edu.cmart.model.request.LoginRequest;
import edu.cmart.model.request.RegisterRequest;
import edu.cmart.model.response.JwtAuthenticationResponse;

// Lớp này sẽ trả về token cho người dùng sau khi đăng ký hoặc đăng nhập thành công
public interface AuthenticationService {
    JwtAuthenticationResponse register(RegisterRequest request);

    JwtAuthenticationResponse login(LoginRequest request);
}
