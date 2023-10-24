package edu.cmart.model.dto;

import edu.cmart.entity.enums.Gender;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class AccountDto {

    private Long id;
    private String fullname;
    private String email;
    private String phoneNumber;
    private Gender gender;
    private String image;
    private Boolean isActivated;
    private Date birthday;


}
