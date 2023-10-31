package edu.cmart.controller;

import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import edu.cmart.exception.core.ArchitectureException;
import edu.cmart.facade.GoogleMapFacade;
import edu.cmart.model.common.ResponseHandler;
import edu.cmart.model.request.DistanceRequest;
import edu.cmart.service.GoogleMapService;
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

import java.io.IOException;

import static edu.cmart.util.api.ConstantsApi.GoogleMap.GOOGLE_MAP_PATH;


@RestController
@RequiredArgsConstructor
@RequestMapping(GOOGLE_MAP_PATH)
@Tag(name = "GoogleMapController", description = "Functionality for gg map")
public class GoogleMapController {

    private final GoogleMapFacade googleMapFacade;


    /**
     * Anyone can access
     */
    @Operation(summary = "Get distance gg map", description = "Get distance gg map, anyone can access")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = DistanceMatrix.class),
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
                    description = "response failed maybe distance not found or error param",
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
    @PostMapping("/distance")
    public ResponseEntity<Object> getDistance(
            @RequestBody DistanceRequest distanceRequest
            ) throws IOException, InterruptedException, ApiException, ArchitectureException {
        return ResponseHandler.response(HttpStatus.OK,
                googleMapFacade.getDistance(distanceRequest), true);
    }

    @Operation(summary = "Get coordinates gg map", description = "Get coordinates gg map, anyone can access")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = GeocodingResult[].class),
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
                    description = "response failed maybe coordinates not found or error param",
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
    @PostMapping("/get-coordinates")
    public ResponseEntity<Object> getCoordinates(
            @RequestBody String[] addresses
    ) throws IOException, InterruptedException, ApiException, ArchitectureException {
        return ResponseHandler.response(HttpStatus.OK,
                googleMapFacade.getCoordinates(addresses), true);
    }

    @Operation(summary = "Get address gg map", description = "Get address gg map, anyone can access")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = GeocodingResult[].class),
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
                    description = "response failed maybe address not found or error param",
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
    @PostMapping("/get-address")
    public ResponseEntity<Object> getAddress(
            @RequestBody LatLng latLng
    ) throws IOException, InterruptedException, ApiException, ArchitectureException {
        return ResponseHandler.response(HttpStatus.OK,
                googleMapFacade.getAddress(latLng), true);
    }
}
