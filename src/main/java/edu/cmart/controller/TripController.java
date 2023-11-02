package edu.cmart.controller;

import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import edu.cmart.exception.core.ArchitectureException;
import edu.cmart.facade.TripFacade;
import edu.cmart.model.common.ResponseHandler;
import edu.cmart.model.request.DistanceRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static edu.cmart.util.api.ConstantsApi.Trip.TRIP_PATH;

@RestController
@RequiredArgsConstructor
@RequestMapping(TRIP_PATH)
@Tag(name = "TripController", description = "Functionality for trip")
public class TripController {

    private final TripFacade tripFacade;

    /**
     * Anyone can access
     */
    @PostMapping("/service-car/{serviceCarId}")
    @Operation(summary = "Get trip for service car and latlng",
            description = "Get trip information for payment, anyone can access")
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
    public ResponseEntity<Object> getTripByServiceCarAndLatLng(
            @RequestBody DistanceRequest request,
            @PathVariable Long serviceCarId
    ) throws IOException, InterruptedException, ApiException, ArchitectureException {
        return ResponseHandler.response(HttpStatus.OK, tripFacade.getTripByServiceCarAndLatLng(request, serviceCarId), true);
    }

}
