package edu.cmart.controller;

import edu.cmart.exception.core.ArchitectureException;
import edu.cmart.facade.DistrictFacade;
import edu.cmart.model.common.ResponseHandler;
import edu.cmart.model.dto.DistrictDto;
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

import static edu.cmart.util.api.ConstantsApi.District.DISTRICT_PATH;

@RestController
@RequiredArgsConstructor
@RequestMapping(DISTRICT_PATH)
@Tag(name = "DistrictController", description = "Functionality for district")
public class DistrictController {


    private final DistrictFacade districtFacade;

    /**
     * Anyone can access
     */
    @GetMapping
    @Operation(summary = "Find all district", description = "Find all district, anyone can access")
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
                    description = "response failed maybe district not found or error param",
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
                districtFacade.findAll(new SearchCriteria(page, size, columSort)), true);
    }

    /**
     * Anyone can access
     */
    @GetMapping("/city/{cityId}")
    @Operation(summary = "Find all district by city id", description = "Find all district by city id, anyone can access")
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
                    description = "response failed maybe district not found or error param",
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
    public ResponseEntity<Object> findAllByCityId(
            @PathVariable Long cityId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "id") String columSort
    ) throws ArchitectureException {
        return ResponseHandler.response(HttpStatus.OK,
                districtFacade.findAllByCityId(cityId, new SearchCriteria(page, size, columSort)), true);
    }

    /**
     * Anyone can access
     */
    @GetMapping("/name/{name}")
    @Operation(summary = "Find all district by name", description = "Find all district by name, anyone can access")
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
                            description = "response failed maybe district not found or error param",
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
                districtFacade.findAllByName(name, new SearchCriteria(page, size, columSort)), true);
    }

    /**
     * Anyone can access
     */
    @GetMapping("/id/{districtId}")
    @Operation(summary = "Find district by id", description = "Find district by id, anyone can access")
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
                    description = "response failed maybe districtId is null or error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = String.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "response failed maybe district not found or error param",
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
    public ResponseEntity<Object> findById(@PathVariable Long districtId) throws ArchitectureException {
        return ResponseHandler.response(HttpStatus.OK, districtFacade.findById(districtId), true);
    }

    /**
     * Only admin can access
     */
    @PostMapping("/create")
    @Operation(summary = "Create district", description = "Create district, only admin can access")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = DistrictDto.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "response failed maybe districtDto is null or district is exist",
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
    public ResponseEntity<Object> create(@RequestBody DistrictDto districtDto) throws ArchitectureException {
        return ResponseHandler.response(HttpStatus.OK, districtFacade.create(districtDto), true);
    }

    /**
     * Only admin can access
     */
    @PutMapping("/update/{districtId}")
    @Operation(summary = "Update district", description = "Update district, only admin can access")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = DistrictDto.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "response failed maybe districtDto is null or error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = String.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "response failed maybe district not found or error param",
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
    public ResponseEntity<Object> update(@PathVariable Long districtId, @RequestBody DistrictDto districtDto) throws ArchitectureException {
        return ResponseHandler.response(HttpStatus.OK, districtFacade.update(districtId, districtDto), true);
    }

    /**
     * Only admin can access
     */
    @DeleteMapping("/delete/{districtId}")
    @Operation(summary = "Delete district", description = "Delete district, only admin can access")
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
                    description = "response failed maybe districtId is null or error param",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = String.class),
                                    mediaType = "application/json"
                            )

                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "response failed maybe district not found or error param",
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
    public ResponseEntity<Object> delete(@PathVariable Long districtId) throws ArchitectureException {
        districtFacade.delete(districtId);
        return ResponseHandler.response(HttpStatus.OK, "Delete successfully!", true);
    }

}

