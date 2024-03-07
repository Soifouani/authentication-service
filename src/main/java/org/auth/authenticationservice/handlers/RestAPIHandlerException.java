package org.auth.authenticationservice.handlers;

import org.auth.authenticationservice.dtos.ApiResponseDTO;
import org.auth.authenticationservice.enums.ApiResponseStatus;
import org.auth.authenticationservice.exceptions.BadCredentialsException;
import org.auth.authenticationservice.exceptions.GrantTypeException;
import org.auth.authenticationservice.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class RestAPIHandlerException {
    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<ApiResponseDTO<?>> resourceNotFoundExceptionHandler(ResourceNotFoundException e) {
        return ResponseEntity
                .status(NOT_FOUND)
                .body(ApiResponseDTO.builder()
                        .success(false)
                        .status(ApiResponseStatus.ERROR)
                        .message(e.getMessage())
                        .data(null)
                        .build());
    }

    @ExceptionHandler(value = {GrantTypeException.class})
    public ResponseEntity<ApiResponseDTO<?>> grantTypeExceptionHandler(GrantTypeException e) {
        return ResponseEntity
            .status(BAD_REQUEST)
            .body(ApiResponseDTO.builder()
                .success(false)
                .status(ApiResponseStatus.ERROR)
                .message(e.getMessage())
                .data(null)
                .build());
    }

    @ExceptionHandler(value = {BadCredentialsException.class})
    public ResponseEntity<ApiResponseDTO<?>> badCredentialsExceptionHandler(BadCredentialsException e) {
        return ResponseEntity
            .status(UNAUTHORIZED)
            .body(ApiResponseDTO.builder()
                .success(false)
                .status(ApiResponseStatus.ERROR)
                .message(e.getMessage())
                .data(null)
                .build());
    }

    @ExceptionHandler(value = {JwtException.class})
    public ResponseEntity<ApiResponseDTO<?>> jwtExceptionHandler(JwtException e) {
        return ResponseEntity
            .status(UNAUTHORIZED)
            .body(ApiResponseDTO.builder()
                .success(false)
                .status(ApiResponseStatus.ERROR)
                .message(e.getMessage())
                .data(null)
                .build());
    }
}
