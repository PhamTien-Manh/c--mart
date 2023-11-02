package edu.cmart.controller;

import edu.cmart.exception.core.ArchitectureException;
import edu.cmart.facade.PaymentFacade;
import edu.cmart.model.common.ResponseHandler;
import edu.cmart.model.dto.PaymentDto;
import edu.cmart.model.dto.SearchCriteria;
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

import java.util.List;

import static edu.cmart.util.api.ConstantsApi.Payment.PAYMENT_PATH;

@RestController
@RequiredArgsConstructor
@RequestMapping(PAYMENT_PATH)
@Tag(name = "PaymentController", description = "Functionality for payment")
public class PaymentController {

    private final PaymentFacade paymentFacade;

    /**
     * Anyone can access
     */
    @GetMapping
    @Operation(summary = "Find all payment", description = "Find all payment, anyone can access")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = List.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "response failed maybe promo not found or error param",
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
                    }
            )}
    )
    public ResponseEntity<Object> findAll() throws ArchitectureException {
        return ResponseHandler.response(HttpStatus.OK, paymentFacade.findAll(), true);
    }

    /**
     * Anyone can access
     */
    @GetMapping("/id/{paymentId}")
    @Operation(summary = "Find payment by id", description = "Find payment by id, anyone can access")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = PaymentDto.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "response failed maybe payment not found or error param",
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
                    }
            )}
    )
    public ResponseEntity<Object> findById(@PathVariable Long paymentId) throws ArchitectureException {
        return ResponseHandler.response(HttpStatus.OK, paymentFacade.findById(paymentId), true);
    }

    /**
     * Just admin can access
     */
    @PostMapping("/create")
    @Operation(summary = "Create payment", description = "Create payment, just admin can access")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = PaymentDto.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "response failed maybe id is not null",
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
                    }
            )}
    )
    public ResponseEntity<Object> create(@RequestBody PaymentDto paymentDto) throws ArchitectureException {
        return ResponseHandler.response(HttpStatus.CREATED, paymentFacade.create(paymentDto), true);
    }

    /**
     * Just admin can access
     */
    @PutMapping("/update/{paymentId}")
    @Operation(summary = "Update payment", description = "Update payment, just admin can access")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = PaymentDto.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "response failed maybe payment not found or error param",
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
                    }
            )}
    )
    public ResponseEntity<Object> update(
            @RequestBody PaymentDto paymentDto,
            @PathVariable Long paymentId) throws ArchitectureException {
        return ResponseHandler.response(HttpStatus.OK,
                paymentFacade.update(paymentDto, paymentId), true);
    }

    /**
     * Just admin can access
     */
    @DeleteMapping("/delete/{paymentId}")
    @Operation(summary = "Delete payment", description = "Delete payment, just admin can access")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = String.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "response failed maybe payment not found or error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ArchitectureException.class),
                                    mediaType = "application/json"
                            )
                    }
            )}
    )
    public ResponseEntity<Object> deleteById(@PathVariable Long paymentId) throws ArchitectureException {
        paymentFacade.deleteById(paymentId);
        return ResponseHandler.response(HttpStatus.OK, "Payment with id " + paymentId + " deleted", true);
    }
}
