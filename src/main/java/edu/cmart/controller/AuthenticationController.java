package edu.cmart.controller;

import edu.cmart.exception.core.ArchitectureException;
import edu.cmart.facade.AuthenticationFacade;
import edu.cmart.model.request.LoginRequest;
import edu.cmart.model.request.RegisterRequest;
import edu.cmart.model.response.JwtAuthenticationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static edu.cmart.util.api.ConstantsApi.Auth.AUTH_PATH;

@RestController
@RequestMapping(AUTH_PATH)
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "API for Authentication login and register")
public class AuthenticationController {
    private final AuthenticationFacade authenticationFacade;

    /**
     * Only user can register
     */
    @PostMapping("/register")
    @Operation(summary = "Register new account", description = "Only user can register")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Register successfully",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = JwtAuthenticationResponse.class),
                                    mediaType = "application/json"
                            )
                    }

            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Register failed, maybe phone number already exists",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ArchitectureException.class),
                                    mediaType = "application/json"
                            )
                    }

            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Register failed, maybe phone number oversize or missing field",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ArchitectureException.class),
                                    mediaType = "application/json"
                            )
                    }

            )
    })
    public ResponseEntity<JwtAuthenticationResponse> register(@RequestBody RegisterRequest request) throws ArchitectureException {
        return ResponseEntity.ok(authenticationFacade.register(request));
    }

    /**
     * Anyone can log in
     */
    @PostMapping("/login")
    @Operation(summary = "Login account", description = "Anyone can log in")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Login successfully",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = JwtAuthenticationResponse.class),
                                    mediaType = "application/json"
                            )
                    }

            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Login failed, maybe phone number not found",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ArchitectureException.class),
                                    mediaType = "application/json"
                            )
                    }

            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Login failed, maybe phone number or password is missing",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ArchitectureException.class),
                                    mediaType = "application/json"
                            )
                    }

            )
    })
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody LoginRequest request) throws ArchitectureException {
        return ResponseEntity.ok(authenticationFacade.login(request));
    }
}
