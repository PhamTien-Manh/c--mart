package edu.cmart.service;

import org.springframework.security.core.userdetails.UserDetails;

// lớp này sẽ trích xuất thông tin người dùng từ token, tạo token và kiểm tra tính hợp lệ của token
public interface JwtService {
    String extractUserName(String token);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);
}
