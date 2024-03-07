package org.auth.authenticationservice.web;

import lombok.AllArgsConstructor;
import org.auth.authenticationservice.dtos.ApiResponseDTO;
import org.auth.authenticationservice.dtos.AuthRequestDTO;
import org.auth.authenticationservice.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationRestAPI {
    private final AuthenticationService authenticationService;

    @PostMapping(path = "/public/login")
    public ResponseEntity<ApiResponseDTO<Map<String, String>>> authenticate(@RequestBody AuthRequestDTO authRequestDTO) {
        return ResponseEntity
            .status(OK)
            .body(authenticationService.authenticate(authRequestDTO));
    }
}
