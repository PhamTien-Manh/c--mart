package edu.cmart.facade;

import edu.cmart.entity.Role;
import edu.cmart.entity.enums.TypeRoles;
import edu.cmart.exception.common.CanNotChangeException;
import edu.cmart.exception.core.ArchitectureException;
import edu.cmart.exception.entity.EntityAlreadyExistException;
import edu.cmart.exception.entity.EntityNotFoundException;
import edu.cmart.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleFacade {
    private final RoleRepository roleRepository;

    // Kiểm tra sdt đã có tài khoản trước đó với role tương ứng chưa
    public List<Role> checkRole(String phoneNumber, String role) throws ArchitectureException {
        List<Role> roles = roleRepository.findAllByAccountPhoneNumber(phoneNumber);
        String prefix = role.substring(0, 5).toUpperCase();

            for (Role roleOld : roles) {
                if (roleOld.getTypeRoles().name().contains(prefix))
                    throw new EntityAlreadyExistException();
            }

        return roles;
    }
    // Kiểm tra xem tài khoản có role tương ứng chưa
    public Role getRole(Long accountId, String typeRole) throws ArchitectureException {
        List<Role> roles = roleRepository.findByAccountId(accountId);
        String prefix = typeRole.substring(0, 5);

        if (roles.isEmpty())
            throw new EntityNotFoundException();

        for (Role roleOld : roles) {
            if (roleOld.getTypeRoles().name().contains(prefix))
                return roleOld;
        }

        throw new CanNotChangeException("Role account");
    }

    public List<Role> hasOtherRole(Long accountId) throws ArchitectureException {
        List<Role> roles = roleRepository.findByAccountId(accountId);

        if (roles.isEmpty())
            throw new EntityNotFoundException();

        for (Role roleOld : roles) {
            if (!roleOld.getTypeRoles().name().contains(TypeRoles.USER_ACTIVE.name()))
                throw new CanNotChangeException("account");
        }

        return roles;
    }

    public Role saveRole(Role role) throws ArchitectureException{
        return roleRepository.save(role);
    }
}
