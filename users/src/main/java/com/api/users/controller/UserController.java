package com.api.users.controller;

import com.api.users.entity.User;
import com.api.users.exceptionHandler.UserNotFoundException;
import com.api.users.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Add a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<Void> save(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Get user by document type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("getUser/{documentType}/{document}")
    public ResponseEntity<User> getUserByTypeAndDocument(@PathVariable("documentType") @NotNull String documentType,
                                                         @PathVariable("document") @NotNull String document) throws UserNotFoundException {
        User user = userService.getUserByTypeAndDocument(documentType, document);
        return (user != null) ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }
}
