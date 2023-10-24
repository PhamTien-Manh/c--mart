package edu.cmart.model.request;

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
public class RegisterRequest {
    private String phoneNumber;
    private String fullName;
    private Gender gender;
    private Date birthday;
    private String password;
    private String quickPassword;

}
