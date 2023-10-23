package edu.cmart.model.dto;

import edu.cmart.entity.enums.Gender;
import edu.cmart.entity.enums.Role;
import jakarta.persistence.Transient;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

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
