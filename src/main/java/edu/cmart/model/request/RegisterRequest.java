package edu.cmart.model.request;

import edu.cmart.entity.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class RegisterRequest {
    @NonNull
    private String phoneNumber;
    @NonNull
    private String fullname;
    @NonNull
    private Gender gender;
    @NonNull
    private Date birthday;
    @NonNull
    private String password;
    @NonNull
    private String quickPassword;
    private String citizenIdentificationCard;

}
