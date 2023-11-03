package edu.cmart.model.response;

import edu.cmart.entity.Account;
import edu.cmart.entity.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthenticationResponse{

    private Long id;
    private String fullname;
    private String email;
    private String phoneNumber;
    private Gender gender;
    private String image;
    private Boolean isActivated;
    private Date birthday;
    private String accessToken;
    private String refreshToken;


    public static JwtAuthenticationResponse apply(
            Account account,
            String accessToken,
            String refreshToken) {
        return JwtAuthenticationResponse
                .builder()
                .id(account.getId())
                .fullname(account.getFullname())
                .email(account.getEmail())
                .phoneNumber(account.getPhoneNumber())
                .gender(account.getGender())
                .image(account.getImage())
                .isActivated(account.getIsActivated())
                .birthday(account.getBirthday())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
