package edu.cmart.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final UserService userService;

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
        final String userEmail;
        // Nếu request không chứa Header là Authorization và prefix của token là Bearer
        // thì chúng ta sẽ bỏ qua filter này
        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        // Lấy thông tin người dùng từ token
        userEmail = jwtService.extractUserName(jwt);
        // Nếu token hợp lệ và người dùng chưa đăng nhập
        // thì chúng ta sẽ lưu thông tin người dùng vào SecurityContext
        if (StringUtils.isNotEmpty(userEmail)
                && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userService.userDetailsService()
                    .loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(jwt, userDetails)) {
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authToken);
                SecurityContextHolder.setContext(context);
            }
        }
        filterChain.doFilter(request, response);
    }
}
