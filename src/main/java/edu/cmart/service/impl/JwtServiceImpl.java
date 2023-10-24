package edu.cmart.service.impl;

import edu.cmart.entity.Account;
import edu.cmart.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static edu.cmart.util.method.RoleValueIndex.getRoles;


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
    public Account extractUserName(String token) {
        Claims claims = extractAllClaims(token);

        String phoneNumber = claims.get("phoneNumber", String.class);
        Long accountId = claims.get("accountId", Long.class);
        List<Integer> roles = claims.get("roles", List.class);

        Account account = new Account();
        account.setId(accountId);
        account.setPhoneNumber(phoneNumber);
        account.setRoles(getRoles(roles));
        return account;
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
    public String generateToken(Account account, List<Integer> roles) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("phoneNumber", account.getPhoneNumber());
        extraClaims.put("accountId", account.getId());
        extraClaims.put("roles", roles);
        return generateToken(extraClaims);
    }
    private String generateToken(Map<String, Object> extraClaims) {
        return Jwts.builder().setClaims(extraClaims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                // Đoạn này sẽ tạo ra chữ ký cho token của người dùng bằng thuật toán HS256
                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }
    /* Phương thức này sẽ kiểm tra xem token có hợp lệ hay không
    *  Thông qua tên người dùng và thời gian hết hạn của token
    */
    @Override
    public boolean isTokenValid(String token) {
        return !isTokenExpired(token);
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
