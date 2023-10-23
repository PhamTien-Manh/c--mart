package edu.cmart.service.impl;

import com.truongbn.security.dao.request.SignInRequest;
import com.truongbn.security.dao.request.SignUpRequest;
import com.truongbn.security.dao.response.JwtAuthenticationResponse;
import com.truongbn.security.entities.Role;
import com.truongbn.security.entities.User;
import com.truongbn.security.repository.UserRepository;
import com.truongbn.security.service.AuthenticationService;
import com.truongbn.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /*
    *  Phương thức signup() nhận vào thông tin đăng ký của người dùng và trả về
    *  Sẽ chuyển thông tin đăng ký của người dùng thành đối tượng User và lưu vào cơ sở dữ liệu.
    *  Sau đó, sẽ tạo ra một chuỗi JWT và trả về cho người dùng.
    * */
    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        // Chuyển đổi thông tin
        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        // Lưu
        userRepository.save(user);
        // Tạo chuỗi JWT
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }


    /*
    *  Phương thức signin() nhận vào thông tin đăng nhập của người dùng và trả về
    *
    * */
    @Override
    public JwtAuthenticationResponse signin(SignInRequest request) {
        // đoạn này sẽ kiểm tra thông tin đăng nhập của người dùng
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        // Tạo chuỗi JWT
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
