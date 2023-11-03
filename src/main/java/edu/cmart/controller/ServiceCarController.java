package edu.cmart.controller;

import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import edu.cmart.exception.core.ArchitectureException;
import edu.cmart.facade.ServiceCarFacade;
import edu.cmart.model.common.ResponseHandler;
import edu.cmart.model.dto.SearchCriteria;
import edu.cmart.model.dto.ServiceCarDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

import static edu.cmart.util.api.ConstantsApi.ServiceCar.SERVICE_CAR_PATH;

@RestController
@RequiredArgsConstructor
@RequestMapping(SERVICE_CAR_PATH)
@Tag(name = "ServiceCarController", description = "Functionality for service car")
public class ServiceCarController {

    private final ServiceCarFacade serviceCarFacade;

    /**
     * Anyone can access
     */
    @PostMapping("/find-all-by-geocode")
    @Operation(summary = "Find service by Geocode", description = "Find all service by Geocode, anyone can access")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ArrayList.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "response failed maybe error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ArchitectureException.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "response failed maybe service not found or error param",
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
    public ResponseEntity<Object> findAllByGeoCode(
            @RequestBody LatLng latLng
    ) throws IOException, InterruptedException, ApiException, ArchitectureException {
        return ResponseHandler.response(HttpStatus.OK, serviceCarFacade.findAllByCityAndDistrict(latLng), true);
    }

    /**
     * Anyone can access
     */
    @GetMapping
    @Operation(summary = "Find all service", description = "Find all service, anyone can access")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ArrayList.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "response failed maybe service not found or error param",
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
    public ResponseEntity<Object> findAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "id") String columSort
    ) throws ArchitectureException {
        return ResponseHandler.response(HttpStatus.OK, serviceCarFacade.findAll(new SearchCriteria(page, size, columSort)), true);
    }

    /**
     * Anyone can access
     */
    @GetMapping("/type-vehicle/{typeVehicleId}")
    @Operation(summary = "Find all service by type vehicle", description = "Find all service by type vehicle, anyone can access")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ArrayList.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "response failed maybe type vehicle id is null or error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ArchitectureException.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "response failed maybe service not found or error param",
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
    public ResponseEntity<Object> findAllByTypeVehicle(
            @PathVariable Long typeVehicleId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "id") String columSort
    ) throws ArchitectureException {
        return ResponseHandler.response(HttpStatus.OK,
                serviceCarFacade.findAllByTypeVehicleId(typeVehicleId,
                        new SearchCriteria(page, size, columSort)), true);
    }

    /**
     * Anyone can access
     */
    @GetMapping("/city/{cityId}")
    @Operation(summary = "Find all service by city", description = "Find all service by city, anyone can access")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ArrayList.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "response failed maybe city id is null or error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ArchitectureException.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "response failed maybe service not found or error param",
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
    public ResponseEntity<Object> findAllByCity(
            @PathVariable Long cityId
    ) throws ArchitectureException {
        return ResponseHandler.response(HttpStatus.OK, serviceCarFacade.findAllByCityId(cityId), true);
    }

    /**
     * Anyone can access
     */
    @GetMapping("/id/{serviceCarId}")
    @Operation(summary = "Find service by id", description = "Find service by id, anyone can access")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ServiceCarDto.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "response failed maybe service car id is null or error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ArchitectureException.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "response failed maybe service car not found or error param",
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
    public ResponseEntity<Object> findById(@PathVariable Long serviceCarId) throws ArchitectureException {
        return ResponseHandler.response(HttpStatus.OK, serviceCarFacade.findById(serviceCarId), true);
    }

    /**
     * Just admin - staff system can access
     */
    @PostMapping("/create")
    @Operation(summary = "Create service", description = "Create service, just admin - staff system can access")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ServiceCarDto.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "response failed maybe service car dto is null or error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ArchitectureException.class),
                                    mediaType = "application/json"
                            )
                    }

            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "response failed maybe service car not found or error param",
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
    public ResponseEntity<Object> create(@RequestBody ServiceCarDto dto) throws ArchitectureException {
        return ResponseHandler.response(HttpStatus.OK, serviceCarFacade.create(dto), true);
    }

    /**
     * Just admin - staff system can access
     */
    @PutMapping("/update/{serviceCarId}")
    @Operation(summary = "Update service", description = "Update service, just admin - staff system can access")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ServiceCarDto.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "response failed maybe service car id is null or service car dto is null or error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ArchitectureException.class),
                                    mediaType = "application/json"
                            )

                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "response failed maybe service car not found or error param",
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
    public ResponseEntity<Object> update(@PathVariable Long serviceCarId, @RequestBody ServiceCarDto dto) throws ArchitectureException {
        return ResponseHandler.response(HttpStatus.OK, serviceCarFacade.update(serviceCarId, dto), true);
    }

    /**
     * Just admin - staff system can access
     */
    @PutMapping("/is-actived/{serviceCarId}")
    @Operation(summary = "Set active service", description = "Set active service, just admin - staff system can access")
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
                    description = "response failed maybe service car id is null or is actived is null or error param",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )

                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "response failed maybe service car not found or error param",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )

                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "response failed maybe missing field or error param",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )

                    }
            )}
    )
    public ResponseEntity<Object> isActived(@PathVariable Long serviceCarId, @RequestParam Boolean isActived) throws ArchitectureException {
        serviceCarFacade.isActived(serviceCarId, isActived);
        return ResponseHandler.response(HttpStatus.OK, "Set active successfully!", true);
    }
}
