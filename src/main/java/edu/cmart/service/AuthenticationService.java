package edu.cmart.service;

import com.truongbn.security.dao.request.SignInRequest;
import com.truongbn.security.dao.request.SignUpRequest;
import com.truongbn.security.dao.response.JwtAuthenticationResponse;

// Lớp này sẽ trả về token cho người dùng sau khi đăng ký hoặc đăng nhập thành công
public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SignInRequest request);
}
