package edu.cmart.service;

import edu.cmart.entity.Account;

import java.util.List;

// lớp này sẽ trích xuất thông tin người dùng từ token, tạo token và kiểm tra tính hợp lệ của token
public interface JwtService {
    Account extractUserName(String token);

    String generateToken(Account account, List<Integer> roles);

    boolean isTokenValid(String token);
}
