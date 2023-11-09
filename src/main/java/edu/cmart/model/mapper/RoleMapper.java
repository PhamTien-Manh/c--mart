package edu.cmart.model.mapper;

import edu.cmart.entity.Role;
import edu.cmart.model.dto.RoleDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class RoleMapper implements Function<Role, RoleDto> {
    @Override
    public RoleDto apply(Role role) {
        return RoleDto.builder()
                .id(role.getId())
                .typeRoles(role.getTypeRoles())
                .accountId(role.getAccount().getId())
                .build();
    }

    public Role applyToRole(RoleDto roleDto) {
        return Role.builder()
                .id(roleDto.getId())
                .typeRoles(roleDto.getTypeRoles())
                .account(null)
                .build();
    }
}
