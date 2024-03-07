package org.auth.authenticationservice.services;

import org.auth.authenticationservice.dtos.ApiResponseDTO;
import org.auth.authenticationservice.dtos.AuthRequestDTO;

import java.util.Map;

public interface AuthenticationService {

    ApiResponseDTO<Map<String,String>> authenticate(AuthRequestDTO authRequestDTO);
}
