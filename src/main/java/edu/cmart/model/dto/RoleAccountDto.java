package edu.cmart.model.dto;

import edu.cmart.entity.Account;
import edu.cmart.entity.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class RoleAccountDto {
    private Long id;
    private Role role;

    private Long accountId;
}
