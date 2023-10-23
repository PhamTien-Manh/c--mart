package edu.cmart.service.impl;

import com.truongbn.security.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
/*
* Lớp này sẽ xử lý token
* */
@Service
public class JwtServiceImpl implements JwtService {
    // Đây là token của trang web, nên nó sẽ được lưu trong file application.properties
    @Value("${token.signing.key}")
    private String jwtSigningKey;
    // Phương thức này sẽ trích xuất tên người dùng từ token
    @Override
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }
    // Lấy thông tin người dùng cuối cùng từ token
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody();
    }
    // Phương thức này sẽ tạo ra token từ thông tin người dùng
    @Override
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }
    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                // Đoạn này sẽ tạo ra chữ ký cho token của người dùng bằng thuật toán HS256
                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }
    /* Phương thức này sẽ kiểm tra xem token có hợp lệ hay không
    *  Thông qua tên người dùng và thời gian hết hạn của token
    */
    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
    // Phương thức này sẽ kiểm tra xem token có hết hạn hay không
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Phương thức này sẽ trả về đối tượng Key dùng để tạo ra token
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
