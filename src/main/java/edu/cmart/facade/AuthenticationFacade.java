package edu.cmart.facade;

import edu.cmart.entity.Role;
import edu.cmart.exception.core.ArchitectureException;
import edu.cmart.exception.entity.EntityNotFoundException;
import edu.cmart.model.request.LoginRequest;
import edu.cmart.model.request.RegisterRequest;
import edu.cmart.model.response.JwtAuthenticationResponse;
import edu.cmart.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationFacade {
    private final AuthenticationManager authenticationManager;
    private final AuthenticationService authenticationService;
    private final RoleFacade roleFacade;

    public JwtAuthenticationResponse register(RegisterRequest request) throws ArchitectureException {
        // Tìm tất cả role của phoneNumber
        List<Role> roles = roleFacade.checkRole(request.getPhoneNumber(), "USER_");
        return authenticationService.register(request, roles);
    }

    public JwtAuthenticationResponse login(LoginRequest request) throws ArchitectureException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getPhoneNumber(), request.getPassword()));

        List<Role> roles = roleFacade.checkRole(request.getPhoneNumber(), "NOTHING");

        if (roles.isEmpty()) throw new EntityNotFoundException();

        return authenticationService.login(request, roles);
    }

}
