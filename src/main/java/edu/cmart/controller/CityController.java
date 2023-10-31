package edu.cmart.controller;

import edu.cmart.exception.core.ArchitectureException;
import edu.cmart.facade.CityFacade;
import edu.cmart.model.common.ResponseHandler;
import edu.cmart.model.dto.CityDto;
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

import static edu.cmart.util.api.ConstantsApi.City.CITY_PATH;


@RestController
@RequiredArgsConstructor
@RequestMapping(CITY_PATH)
@Tag(name = "CityController", description = "Functionality for city")
public class CityController {

    private final CityFacade cityFacade;

    /**
     * Anyone can access
     */
    @GetMapping
    @Operation(summary = "Find all city", description = "Find all city, anyone can access")
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
                    description = "response failed maybe city not found or error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = String.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "response failed maybe missing field or error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = String.class),
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
        return ResponseHandler.response(HttpStatus.OK,
                cityFacade.findAll(new SearchCriteria(page, size, columSort)), true);
    }

    /**
     * Anyone can access
     */
    @GetMapping("/name/{name}")
    @Operation(summary = "Find all city by name", description = "Find all city by name, anyone can access")
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
                    responseCode = "400",
                    description = "response failed maybe name is null or error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = String.class),
                                    mediaType = "application/json"
                            )
                    }

            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "response failed maybe city not found or error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = String.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "response failed maybe missing field or error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = String.class),
                                    mediaType = "application/json"
                            )
                    }
            )}
    )
    public ResponseEntity<Object> findAllByName(
            @PathVariable String name,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "id") String columSort
    ) throws ArchitectureException {
        return ResponseHandler.response(HttpStatus.OK,
                cityFacade.findAllByName(name, new SearchCriteria(page, size, columSort)), true);
    }

    /**
     * Anyone can access
     */
    @GetMapping("id/{cityId}")
    @Operation(summary = "Find city by id", description = "Find city by id, anyone can access")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = CityDto.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "response failed maybe cityId is null or error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = String.class),
                                    mediaType = "application/json"
                            )

                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "response failed maybe city not found or error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = String.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "response failed maybe missing field or error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = String.class),
                                    mediaType = "application/json"
                            )
                    }
            )}
    )
    public ResponseEntity<Object> findById(@PathVariable Long cityId) throws ArchitectureException {
        return ResponseHandler.response(HttpStatus.OK, cityFacade.findById(cityId), true);
    }

    /**
     * Admin can access
     */
    @PostMapping("/create")
    @Operation(summary = "Create city", description = "Create city, admin can access")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = CityDto.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "response failed maybe cityDto is null or city is exist",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = String.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "response failed maybe missing field or error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = String.class),
                                    mediaType = "application/json"
                            )
                    }
            )
    }
    )
    public ResponseEntity<Object> create(@RequestBody CityDto cityDto) throws ArchitectureException {
        return ResponseHandler.response(HttpStatus.OK, cityFacade.create(cityDto), true);
    }

    /**
     * Admin can access
     */
    @PutMapping("/update/{cityId}")
    @Operation(summary = "Update city", description = "Update city, admin can access")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = CityDto.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "response failed maybe cityDto is null or error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = String.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "response failed maybe city not found or error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = String.class),
                                    mediaType = "application/json"
                            )
                    }

            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "response failed maybe missing field or error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = String.class),
                                    mediaType = "application/json"
                            )
                    }
            )
    }
    )
    public ResponseEntity<Object> update(@PathVariable Long cityId, @RequestBody CityDto cityDto) throws ArchitectureException {
        return ResponseHandler.response(HttpStatus.OK, cityFacade.update(cityId, cityDto), true);
    }

    /**
     * Admin can access
     */
    @DeleteMapping("/delete/{cityId}")
    @Operation(summary = "Delete city", description = "Delete city, admin can access")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = @Content(
                            schema = @Schema(implementation = String.class),
                            mediaType = "application/json"
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "response failed maybe cityId is null or error param",
                    content = @Content(
                            schema = @Schema(implementation = String.class),
                            mediaType = "application/json"
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "response failed maybe city not found or error param",
                    content = @Content(
                            schema = @Schema(implementation = String.class),
                            mediaType = "application/json"
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "response failed maybe missing field or error param",
                    content = @Content(
                            schema = @Schema(implementation = String.class),
                            mediaType = "application/json"
                    )
            )
    }
    )
    public ResponseEntity<Object> delete(@PathVariable Long cityId) throws ArchitectureException {
        cityFacade.delete(cityId);
        return ResponseHandler.response(HttpStatus.OK, "Delete successfully!", true);
    }
}
