package org.auth.authenticationservice.web;

import lombok.AllArgsConstructor;
import org.auth.authenticationservice.dtos.ApiResponseDTO;
import org.auth.authenticationservice.dtos.UserRequestDTO;
import org.auth.authenticationservice.dtos.UserResponseDTO;
import org.auth.authenticationservice.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.auth.authenticationservice.constants.Api.SCOPE_ADMIN;
import static org.auth.authenticationservice.constants.Api.SCOPE_USER;
import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/users")
public class UserRestAPI {
    private final UserService userService;

    @PostMapping(path = "/public/create")
    public ResponseEntity<ApiResponseDTO<UserResponseDTO>> addUser(@RequestBody UserRequestDTO userRequestDTO){
        return  ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userService.createUser(userRequestDTO));
    }

    @GetMapping(path = "/profile")
    @PreAuthorize("hasAuthority('"+ SCOPE_ADMIN +"') or " +
            "hasAuthority('"+ SCOPE_USER +"')")
    public ResponseEntity<ApiResponseDTO<UserResponseDTO>> getUserProfile()  {
        return ResponseEntity
            .status(OK)
            .body(userService.getProfile());
    }

    @GetMapping(path = "{userId}")
    @PreAuthorize("hasAuthority('"+ SCOPE_ADMIN +"')")
    public ResponseEntity<ApiResponseDTO<UserResponseDTO>> findUserById(@PathVariable String userId)  {
        return ResponseEntity
            .status(OK)
            .body(userService.findUserByUserId(userId));
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('"+ SCOPE_ADMIN +"')")
    public ResponseEntity<ApiResponseDTO<Page<UserResponseDTO>>> getAllUsers(
            @RequestParam( name = "page", defaultValue = "0" ) int page,
            @RequestParam( name = "size", defaultValue = "5" ) int size ) {
        return ResponseEntity
            .status(OK)
            .body(userService.getAllUsers(page, size));
    }
}
