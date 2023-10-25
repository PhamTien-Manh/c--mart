package edu.cmart.entity.enums;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public enum TypeRoles {
    ADMIN,
    USER_ACTIVE,
    USER_INACTIVE,
    STAFF_SERVICE,
    STAFF_SYSTEM,
    STAFF_INACTIVE,
    DRIVER_ACTIVE,
    DRIVER_INACTIVE;
}
