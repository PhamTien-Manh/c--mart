package edu.cmart.config;

import edu.cmart.entity.Account;
import edu.cmart.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/*
*  JwtAuthenticationFilter sẽ được thực thi mỗi khi có request tới server.
*  Trong filter này, chúng ta sẽ kiểm tra xem request có chứa token hợp lệ hay không.
*  Nếu có, chúng ta sẽ lấy thông tin người dùng từ token và lưu vào SecurityContext.
*  Điều này sẽ giúp Spring Security có thể kiểm tra được thông tin người dùng.
*
* */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    /*
    *  Phương thức doFilterInternal() sẽ được thực thi mỗi khi có request tới server.
    *  Trong filter này, chúng ta sẽ kiểm tra xem request có chứa token có Header là Authorization hay không.
    *  Và prefix của token có phải là Bearer hay không.
    * */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String phoneNumber;
        // Nếu request không chứa Header là Authorization và prefix của token là Bearer
        // thì chúng ta sẽ bỏ qua filter này
        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        // Lấy thông tin người dùng từ token
        Account account = jwtService.extractUserName(jwt);
        /*
        * Kiểm tra lấy thông tin của người dùng
        * Không nhất thiết phải kiểm tra trong database vì thông tin này đã được mã hóa trong token
        * Cùng với việc các dữ liệu lưu trong token là unique và không thể thay đổi nên ta có thể
        * lưu lại token cũ vào SecurityContextHolder để sử dụng lại
        * */
        if (account != null) {

            if (jwtService.isTokenValid(jwt)) {

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        account, null, account.getAuthorities());

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
