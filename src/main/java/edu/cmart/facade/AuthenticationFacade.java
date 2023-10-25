package edu.cmart.facade;

import edu.cmart.entity.Account;
import edu.cmart.entity.Role;
import edu.cmart.entity.enums.TypeRoles;
import edu.cmart.exception.core.ArchitectureException;
import edu.cmart.exception.entity.EntityAlreadyExistException;
import edu.cmart.exception.entity.EntityNotFoundException;
import edu.cmart.model.request.LoginRequest;
import edu.cmart.model.request.RegisterRequest;
import edu.cmart.model.response.JwtAuthenticationResponse;
import edu.cmart.repository.AccountRepository;
import edu.cmart.repository.RoleRepository;
import edu.cmart.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationFacade {
    private final AuthenticationManager authenticationManager;
    private final AuthenticationService authenticationService;
    private final RoleRepository roleRepository;

    public JwtAuthenticationResponse register(RegisterRequest request) throws ArchitectureException {
        // Tìm tất cả role của phoneNumber
        List<Role> roles = roleRepository.findAllByAccountPhoneNumber(request.getPhoneNumber());
        System.out.println(roles);
        // Nếu có thì kiểm tra có role USER chưa
        if (!roles.isEmpty())
            if (
                roles.stream().anyMatch(
                    role -> role.getTypeRoles().equals(TypeRoles.USER_ACTIVE) ||
                    role.getTypeRoles().equals(TypeRoles.USER_INACTIVE)
                )
            )
                throw new EntityAlreadyExistException();
        return authenticationService.register(request, roles);
    }

    public JwtAuthenticationResponse login(LoginRequest request) throws ArchitectureException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getPhoneNumber(), request.getPassword()));

        List<Role> roles = roleRepository.findAllByAccountPhoneNumber(request.getPhoneNumber());

        if (roles.isEmpty()) throw new EntityNotFoundException();

        return authenticationService.login(request, roles);
    }

}
