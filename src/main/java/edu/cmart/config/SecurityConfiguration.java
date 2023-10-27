package edu.cmart.config;

import edu.cmart.entity.enums.TypeRoles;
import edu.cmart.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static edu.cmart.util.api.ConstantsApi.Account.ACCOUNT_PATH;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/*
 *  Lớp này sẽ cấu hình thư viện mã hóa mật khẩu là BCryptPasswordEncoder
 *  và cấu hình AuthenticationManager để Spring Security có thể xác thực thông tin người dùng.
 * Ngoài ra, lớp này cũng sẽ cấu hình SecurityFilterChain để xác thực token của người dùng.
 * Phân quyền truy cập cho các API
 * */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AccountService accountService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        request -> request
                                .requestMatchers(
                                        ACCOUNT_PATH + "/id/**"
                                )
                                .authenticated()
                                // Chỉ có user-active có thể thay đổi thông tin cá nhân
                                .requestMatchers(
                                        ACCOUNT_PATH + "/change-profile/**"
                                )
                                .hasAnyAuthority(
                                        TypeRoles.USER_ACTIVE.name(),
                                        TypeRoles.ADMIN.name()
                                )
                                // Staff sẽ được phép xem thông tin user-driver
                                .requestMatchers(
                                        ACCOUNT_PATH + "/user_inactive/**",
                                        ACCOUNT_PATH + "/user_active/**",
                                        ACCOUNT_PATH + "/driver_inactive/**",
                                        ACCOUNT_PATH + "/driver_active/**"
                                )
                                .hasAnyAuthority(
                                        TypeRoles.STAFF_SYSTEM.name(),
                                        TypeRoles.STAFF_SERVICE.name(),
                                        TypeRoles.ADMIN.name()
                                )
                                // Staff_system được phép chỉnh sửa thông tin cơ bản user-driver-staff
                                .requestMatchers(
                                        ACCOUNT_PATH + "/staff_service/**",
                                        ACCOUNT_PATH + "/staff_inactive/**",
                                        ACCOUNT_PATH + "/driver_active/create/**",
                                        ACCOUNT_PATH + "/user_active/create/**",
                                        ACCOUNT_PATH + "/user_active/set-role/**",
                                        ACCOUNT_PATH + "/driver_active/set-role/**",
                                        ACCOUNT_PATH + "/user_inactive/set-role/**",
                                        ACCOUNT_PATH + "/driver_inactive/set-role/**",
                                        ACCOUNT_PATH + "/update/**"
                                )
                                .hasAnyAuthority(
                                        TypeRoles.STAFF_SYSTEM.name(),
                                        TypeRoles.ADMIN.name()
                                )
                                // Admin toàn quyền
                                .requestMatchers(
                                        ACCOUNT_PATH + "/**"
                                )
                                .hasAnyAuthority(
                                        TypeRoles.ADMIN.name()
                                )
                                .anyRequest()
                                .permitAll())
                .sessionManagement(
                        manager -> manager
                                .sessionCreationPolicy(STATELESS)
                )
                .authenticationProvider(
                        authenticationProvider()
                ).addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Phương thức này dùng để cấu hình AuthenticationManager
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(accountService.userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*")
                        .allowedHeaders("*")
                        .allowedOrigins("*");
            }
        };
    }
}
