package edu.cmart.controller;


import edu.cmart.exception.core.ArchitectureException;
import edu.cmart.facade.TypeVehicleFacade;
import edu.cmart.model.dto.SearchCriteria;
import edu.cmart.model.dto.TypeVehicleDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

import static edu.cmart.util.api.ConstantsApi.TypeVehicle.TYPE_VEHICLE_PATH;

@RestController
@RequiredArgsConstructor
@RequestMapping(TYPE_VEHICLE_PATH)
@Tag(name = "TypeVehicleController", description = "Functionality for TypeVehicle")
public class TypeVehicleController {

    private final TypeVehicleFacade typeVehicleFacade;

    /**
     * Anyone can access
     */
    @GetMapping
    @Operation(summary = "Find all", description = "Find all type vehicle, anyone can access")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = Arrays.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "response failed maybe error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = String.class),
                                    mediaType = "application/json"
                            )
                    }
            )}
    )
    public ResponseEntity<Object> findAll() throws ArchitectureException {
        return ResponseEntity.ok(typeVehicleFacade.findAll());
    }

    /**
     * Anyone can access
     */
    @GetMapping("/id/{typeVehicleId}")
    @Operation(summary = "Find by id", description = "Find type vehicle by id, anyone can access")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = TypeVehicleDto.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "response failed maybe id is null error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = String.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "response failed maybe typevehicle not found in database",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = String.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "response failed maybe error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = String.class),
                                    mediaType = "application/json"
                            )
                    }
            )}
    )
    public ResponseEntity<Object> findById(@PathVariable("typeVehicleId") Long typeVehicleId) throws ArchitectureException {
        return ResponseEntity.ok(typeVehicleFacade.findById(typeVehicleId));
    }

    /**
     * Just admin can access
     */
    @PostMapping("/create")
    @Operation(summary = "Create", description = "Create type vehicle, just admin can access")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = TypeVehicleDto.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "response failed maybe type vehicle dto is null or id not null",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = String.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "response failed maybe missing param or error database",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = String.class),
                                    mediaType = "application/json"
                            )
                    }
            )}
    )
    public ResponseEntity<Object> create(@RequestBody TypeVehicleDto dto) throws ArchitectureException {
        return ResponseEntity.ok(typeVehicleFacade.create(dto));
    }

    /**
     * Just admin can access
     * */
    @PutMapping("/update/{typeVehicleId}")
    @Operation(summary = "Update", description = "Update type vehicle, just admin can access")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = TypeVehicleDto.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "response failed maybe type vehicle dto is null or id is null or not match with path variable type vehicle id or id not null in dto or id not found in database or missing param or error database",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = String.class),
                                    mediaType = "application/json"
                            )
                    }

            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "response failed maybe type vehicle not found in database",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = String.class),
                                    mediaType = "application/json"
                            )
                    }

            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "response failed maybe error database or missing param or error database",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = String.class),
                                    mediaType = "application/json"
                            )
                    }

            )
    }
    )
    public ResponseEntity<Object> update(@PathVariable("typeVehicleId") Long typeVehicleId, @RequestBody TypeVehicleDto dto) throws ArchitectureException {
        return ResponseEntity.ok(typeVehicleFacade.update(typeVehicleId, dto));
    }

    /**
     * Just admin can access
     * */
    @DeleteMapping("/delete/{typeVehicleId}")
    @Operation(summary = "Delete", description = "Delete type vehicle, just admin can access")
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
                    responseCode = "400",
                    description = "response failed maybe type vehicle id is null",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = String.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "response failed maybe type vehicle not found in database",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = String.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "response failed maybe error database or missing param or error database",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = String.class),
                                    mediaType = "application/json"
                            )
                    }
            )
    }
    )
    public ResponseEntity<Object> delete(@PathVariable("typeVehicleId") Long typeVehicleId) throws ArchitectureException {
        typeVehicleFacade.deleteById(typeVehicleId);
        return ResponseEntity.ok("Delete type vehicle success");
    }
}
