package org.auth.authenticationservice.services;

import org.auth.authenticationservice.dtos.ApiResponseDTO;
import org.auth.authenticationservice.dtos.UserRequestDTO;
import org.auth.authenticationservice.dtos.UserResponseDTO;
import org.springframework.data.domain.Page;

public interface UserService {
    ApiResponseDTO<UserResponseDTO> createUser(UserRequestDTO userRequestDTO);

    ApiResponseDTO<UserResponseDTO> getProfile();

    ApiResponseDTO<Page<UserResponseDTO>> getAllUsers(int page, int size);

    ApiResponseDTO<UserResponseDTO> findUserByUserId(String userId);
}
