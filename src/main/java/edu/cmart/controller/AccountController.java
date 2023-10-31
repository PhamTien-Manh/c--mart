package edu.cmart.controller;

import edu.cmart.exception.core.ArchitectureException;
import edu.cmart.facade.AccountFacade;
import edu.cmart.model.common.ResponseHandler;
import edu.cmart.model.dto.AccountDto;
import edu.cmart.model.dto.SearchCriteria;
import edu.cmart.model.request.RegisterRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static edu.cmart.util.api.ConstantsApi.Account.ACCOUNT_PATH;


@RestController
@RequiredArgsConstructor
@RequestMapping(ACCOUNT_PATH)
@Tag(name = "AccountController", description = "Functionality for Account")
public class AccountController {

    private final AccountFacade accountFacade;

    /**
     * Only admin or staff can access
     */
    @GetMapping("{role}")
    @Operation(summary = "Find all account by role", description = "Find all account by role, only admin or staff can access")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = Page.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "response failed maybe role not found or error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ArchitectureException.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "response failed maybe role is not exist or error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ArchitectureException.class),
                                    mediaType = "application/json"
                            )
                    }
            )}
    )
    public ResponseEntity<Object> findAllByRole(
            @PathVariable String role,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "id") String columSort
    ) throws ArchitectureException {
        return ResponseHandler.response(
                HttpStatus.OK,
                accountFacade.findAllByRole(
                        role, new SearchCriteria(page, size, columSort)),
                true);
    }

    /**
     * Only admin can access
     */
    @GetMapping("{role}/gender/{gender}")
    @Operation(
            summary = "Find all account by role and gender",
            description = "Find all account by role and gender, only admin can access")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = Page.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "response failed maybe role and gender not found or error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ArchitectureException.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "response failed maybe role and gender is not exist or error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ArchitectureException.class),
                                    mediaType = "application/json"
                            )
                    }
            )}
    )
    public ResponseEntity<Object> findAllByRoleAndGender(
            @PathVariable String role,
            @PathVariable String gender,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "id") String columSort
    ) throws ArchitectureException {
        return ResponseHandler.response(
                HttpStatus.OK,
                accountFacade.findAllByRoleAndGender(role, gender, new SearchCriteria(page, size, columSort)),
                true);
    }

    /**
     * Only admin can access
     */
    @GetMapping("{role}/isActivated/{isActivated}")
    @Operation(
            summary = "Find all account by role and isActivated",
            description = "Find all account by role and isActivated, only admin can access")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = Page.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "response failed maybe role and isActivated not found or error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ArchitectureException.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "response failed maybe role and isActivated is not exist or error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ArchitectureException.class),
                                    mediaType = "application/json"
                            )
                    }
            )}
    )
    public ResponseEntity<Object> findAllByRoleAndActivated(
            @PathVariable String role,
            @PathVariable Boolean isActivated,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "id") String columSort
    ) throws ArchitectureException {
        return ResponseHandler.response(
                HttpStatus.OK,
                accountFacade.findAllByRoleAndActivated(role, isActivated, new SearchCriteria(page, size, columSort)),
                true);
    }

    /**
     * Only admin can access
     */
    @GetMapping("{role}/fullname/{fullname}")
    @Operation(
            summary = "Find all account by role and fullname",
            description = "Find all account by role and fullname, only admin can access")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = Page.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "response failed maybe role and fullname not found or error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ArchitectureException.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "response failed maybe role and fullname is not exist or error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ArchitectureException.class),
                                    mediaType = "application/json"
                            )
                    }
            )}
    )
    public ResponseEntity<Object> findAllByRoleAndFullname(
            @PathVariable String role,
            @PathVariable String fullname,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "id") String columSort
    ) throws ArchitectureException {
        return ResponseHandler.response(
                HttpStatus.OK,
                accountFacade.findAllByRoleAndFullname(role, fullname, new SearchCriteria(page, size, columSort)),
                true);
    }

    /**
     * Only admin can access
     */
    @GetMapping("{role}/phoneNumber/{phoneNumber}")
    @Operation(
            summary = "Find all account by role and phoneNumber",
            description = "Find all account by role and phoneNumber, only admin can access")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = Page.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "response failed maybe role and phoneNumber not found or error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ArchitectureException.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "response failed maybe role and phoneNumber is not exist or error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ArchitectureException.class),
                                    mediaType = "application/json"
                            )
                    }
            )}
    )
    public ResponseEntity<Object> findAllByRoleAndPhoneNumber(
            @PathVariable String role,
            @PathVariable String phoneNumber,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "id") String columSort
    ) throws ArchitectureException {
        return ResponseHandler.response(
                HttpStatus.OK,
                accountFacade.findAllByRoleAndPhoneNumber(role, phoneNumber, new SearchCriteria(page, size, columSort)),
                true);
    }

    /**
     * Anyone can access
     */
    @GetMapping("/id/{accountId}")
    @Operation(summary = "Find account by id", description = "Find account by id, anyone can access")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = AccountDto.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "response failed maybe account not found or error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ArchitectureException.class),
                                    mediaType = "application/json"
                            )
                    }
            )}
    )
    public ResponseEntity<Object> findById(@PathVariable Long accountId) throws ArchitectureException {
        return ResponseHandler.response(HttpStatus.OK, accountFacade.findById(accountId), true);
    }

    /**
     * Just admin, staff can access
     */
    @PostMapping("/{role}/create")
    @Operation(
            summary = "Create account",
            description = "Add new account with roles staff or driver for database, just admin, staff can access")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = AccountDto.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "response failed maybe accountDto is null or accountDto is exist",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ArchitectureException.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "response failed maybe missing field or error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ArchitectureException.class),
                                    mediaType = "application/json"
                            )
                    })
    }
    )
    public ResponseEntity<Object> create(
            @RequestBody RegisterRequest request,
            @PathVariable String role) throws ArchitectureException {
        return ResponseHandler.response(HttpStatus.OK, accountFacade.create(request, role.toUpperCase()), true);
    }

    /**
     * Just admin, staff can access
     */
    @PutMapping("/update/{accountId}")
    @Operation(
            summary = "Update account",
            description = "Update account for database, just admin, staff can access")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = AccountDto.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "response failed maybe accountDto is null or error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ArchitectureException.class),
                                    mediaType = "application/json"
                            )
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "response failed maybe accountDto is not found or error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ArchitectureException.class),
                                    mediaType = "application/json"
                            )
                    }),
            @ApiResponse(
                    responseCode = "500",
                    description = "response failed maybe missing field or error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ArchitectureException.class),
                                    mediaType = "application/json"
                            )
                    })
    }
    )
    public ResponseEntity<Object> update(
            @PathVariable Long accountId,
            @RequestBody AccountDto accountDto) throws ArchitectureException {
        return ResponseHandler.response(HttpStatus.OK, accountFacade.update(accountId, accountDto), true);
    }

    /**
     * Only admin can access
     */
    @PutMapping("{typeRole}/set-role/{accountId}")
    @Operation(
            summary = "Set other role for account",
            description = "Lock or unlock account for database, only admin can access")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "response failed maybe accountId or isLock is null or error param",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "response failed maybe accountId is not exist or error param",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    })
    }
    )
    public ResponseEntity<Object> setRole(
            @PathVariable Long accountId,
            @PathVariable String typeRole) throws ArchitectureException {
        accountFacade.setRole(accountId, typeRole.toUpperCase());
        return ResponseHandler.response(HttpStatus.OK, "Set role successfully!", true);
    }

    /**
     * Just user can access
     */
    @PutMapping("/change-profile/{accountId}")
    @Operation(
            summary = "Update account",
            description = "Update account for database, just user can access")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = AccountDto.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "response failed maybe accountDto is null or error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ArchitectureException.class),
                                    mediaType = "application/json"
                            )
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "response failed maybe accountDto is not found or error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ArchitectureException.class),
                                    mediaType = "application/json"
                            )
                    }),
            @ApiResponse(
                    responseCode = "500",
                    description = "response failed maybe missing field or error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ArchitectureException.class),
                                    mediaType = "application/json"
                            )
                    })
    }
    )
    public ResponseEntity<Object> changeProfile(
            @PathVariable Long accountId,
            @RequestBody AccountDto accountDto) throws ArchitectureException {
        return ResponseHandler.response(HttpStatus.OK, accountFacade.changeProfile(accountId, accountDto), true);
    }

}
