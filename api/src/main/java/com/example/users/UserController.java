package com.example.users;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.services.users.UserService;
import com.example.users.domain.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

/**
 * Controller class for handling API endpoints related to users.
 *
 * @author Evhen Malysh
 */
@RestController
@RequestMapping("/users")
@Tag(
    name = "Users",
    description = "API operations related to users")
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
            description = "Successfully retrieved the list of users"),
        @ApiResponse(
            responseCode = "404",
            description = "No users found") })
    public List<User> getAll() {
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
            description = "Successfully retrieved the user"),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid ID"),
        @ApiResponse(
            responseCode = "404",
            description = "User with given ID not found") })
    @ResponseStatus(HttpStatus.OK)
    public User getById(@PathVariable @NotNull @Positive final Long id) {
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
            description = "User created successfully"),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid user"),
        @ApiResponse(
            responseCode = "404",
            description = "User with given ID not found") })
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody @NotNull @Valid final User user) {
        return userService.save(user);
    }

    /**
     * Update an existing user.
     *
     * @param id   The ID of the user to update.
     * @param user The User object containing the updated details.
     * @return The updated User object.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing user")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "User updated successfully"),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid Id or user"),
        @ApiResponse(
            responseCode = "404",
            description = "User with given ID not found") })
    @ResponseStatus(HttpStatus.OK)
    public User update(
        @PathVariable @NotNull @Positive final Long id,
        @RequestBody @NotNull @Valid final User user) {
        return userService.update(id, user);
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
            description = "User with given ID not found") })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive final Long id) {
        userService.deleteById(id);
    }
}

