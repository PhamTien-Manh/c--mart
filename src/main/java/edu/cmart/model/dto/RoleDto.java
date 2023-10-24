package edu.cmart.model.dto;

import edu.cmart.entity.enums.TypeRoles;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class RoleDto {
    private Long id;
    private TypeRoles typeRoles;

    private Long accountId;
}
