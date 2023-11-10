package edu.cmart.controller;

import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import edu.cmart.exception.core.ArchitectureException;
import edu.cmart.facade.DriverFacade;
import edu.cmart.model.common.ResponseHandler;
import edu.cmart.model.dto.LocationDriver;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static edu.cmart.util.api.ConstantsApi.Driver.DRIVER_PATH;

@RestController
@RequiredArgsConstructor
@RequestMapping(DRIVER_PATH)
@Tag(name = "DriverController", description = "Functionality for driver")
public class DriverController {


    private final DriverFacade driverFacade;

    /**
     * Anyone can access
     */
    @PostMapping("/update-location")
    @CachePut(value = "services", key = "#locationDriver[0].serviceCarId")
    @Operation(summary = "Update location for driver", description = "Update location for driver, anyone can access")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = LatLng.class),
                                    mediaType = "application/json"
                            )
                    }
            )}
    )
    public List<LocationDriver> updateLocation(@RequestBody List<LocationDriver> locationDriver)  {
        return locationDriver;
    }

    @PostMapping("/get-most-recent/{serviceCarId}")
    public ResponseEntity<Object> getDriverMostRecent(@RequestBody LatLng latLng,
                                                    @PathVariable Long serviceCarId) throws ArchitectureException {
        return ResponseHandler.response(HttpStatus.OK,
                driverFacade.getDriverMostRecent(serviceCarId, latLng), true);
    }

    @PostMapping("/get-accept/{serviceCarId}")
    public ResponseEntity<Object> getDriverAccept(@RequestBody LatLng latLng,
                                     @PathVariable Long serviceCarId)
            throws ArchitectureException, IOException, InterruptedException, ApiException {
        return ResponseHandler.response(HttpStatus.OK,
                driverFacade.getDriverAccept(serviceCarId, latLng), true);
    }

}
