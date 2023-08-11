package com.example.web.users;


import com.example.domain.users.Address;
import com.example.dto.users.UserRegistrationForm;
import com.example.dto.users.UserShortResponse;
import com.example.services.users.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling API endpoints related to users.
 *
 * @author Evhen Malysh
 */
@RestController
@RequestMapping("/users")
@Validated
@Tag(name = "Users", description = "API operations related to users")
@RequiredArgsConstructor
public class UserController {

    /**
     *
     */
    private final UserService userService;

    /**
     * Get a list of all users.
     *
     * @return List of User objects representing all users.
     */
    @GetMapping
    @Operation(summary = "Get a list of all users")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved the list of users",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserShortResponse.class))}),
            @ApiResponse(
                    responseCode = "404",
                    description = "No users found")})
    public List<UserShortResponse> getAll() {
        return userService.getAll();
    }

    /**
     * Get a specific user by ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The User object representing the requested user.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a specific user by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved the user",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserShortResponse.class))}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid ID"),
            @ApiResponse(
                    responseCode = "404",
                    description = "User with given ID not found")})
    @ResponseStatus(HttpStatus.OK)
    public UserShortResponse getById(@PathVariable @NotNull @Positive final Long id) {
        return userService.getById(id);
    }

    /**
     * Create a new user.
     *
     * @param user The User object containing the details of the new user.
     * @return The created User object.
     */
    @PostMapping
    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "User created successfully",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserShortResponse.class))}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid user"),
            @ApiResponse(
                    responseCode = "404",
                    description = "User with given ID not found")})
    @ResponseStatus(HttpStatus.CREATED)
    public UserShortResponse create(@RequestBody @NotNull @Valid final UserRegistrationForm user) {
        return userService.save(user);
    }

    /**
     * Update an existing user`s address.
     *
     * @param id   The ID of the user to update.
     * @param address The user`s address to update.
     * @return The updated user.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing user`s address")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Address updated successfully",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserShortResponse.class))}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid user`s Id or address"),
            @ApiResponse(
                    responseCode = "404",
                    description = "User with given ID not found")})
    @ResponseStatus(HttpStatus.OK)
    public UserShortResponse updateAddress(
            @PathVariable @NotNull @Positive final Long id,
            @RequestBody @NotNull @Valid final Address address) {
        return userService.updateAddress(id, address);
    }

    /**
     * Delete a user by ID.
     *
     * @param id The ID of the user to delete.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "User deleted successfully"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid ID"),
            @ApiResponse(
                    responseCode = "404",
                    description = "User with given ID not found")})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive final Long id) {
        userService.deleteById(id);
    }
}
