package edu.cmart.model.response;

import edu.cmart.entity.Account;
import edu.cmart.entity.enums.TypeRoles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleResponse {
    private Long id;
    private TypeRoles type;
    private Account account;
}
