package org.auth.authenticationservice.services.servicesImpl;

import lombok.AllArgsConstructor;
import org.auth.authenticationservice.dtos.ApiResponseDTO;
import org.auth.authenticationservice.dtos.UserRequestDTO;
import org.auth.authenticationservice.dtos.UserResponseDTO;
import org.auth.authenticationservice.entities.User;
import org.auth.authenticationservice.enums.AccountStatus;
import org.auth.authenticationservice.enums.ApiResponseStatus;
import org.auth.authenticationservice.exceptions.ResourceNotFoundException;
import org.auth.authenticationservice.mappers.UserMapper;
import org.auth.authenticationservice.repositories.RoleRepository;
import org.auth.authenticationservice.repositories.UserRepository;
import org.auth.authenticationservice.services.UserService;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;

import static org.auth.authenticationservice.constants.Api.USER;
import static org.auth.authenticationservice.constants.MessageKey.*;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;

    @Override
    public ApiResponseDTO<UserResponseDTO> createUser(UserRequestDTO userRequestDTO) {

        var role = roleRepository.findByName(USER);

        var user = userMapper.userRequestDTOToUser(userRequestDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(AccountStatus.CREATED);
        user.setCreatedAt(new Date());
        user.setRoles(Collections.singletonList(role));

        return ApiResponseDTO.<UserResponseDTO>builder()
            .success(true)
            .status(ApiResponseStatus.SUCCESS)
            .message(SUCCESS_CREATED_USER)
            .data(userMapper.userToUserResponseDTO(userRepository.save(user)))
            .build();
    }

    @Override
    public ApiResponseDTO<UserResponseDTO> getProfile() {
        return ApiResponseDTO.<UserResponseDTO>builder()
            .success(true)
            .status(ApiResponseStatus.SUCCESS)
            .message(SUCCESS_FOUND_USER_PROFILE)
            .data(
                userRepository
                    .findById(getCurrentUserId()).map(userMapper::userToUserResponseDTO)
                    .orElseThrow(()-> new ResourceNotFoundException(ERROR_FOUND_USER_PROFILE))
            )
            .build();
    }

    @Override
    public ApiResponseDTO<Page<UserResponseDTO>> getAllUsers(int page, int size) {
        return ApiResponseDTO.<Page<UserResponseDTO>>builder()
            .success(true)
            .status(ApiResponseStatus.SUCCESS)
            .message(SUCCESS_FOUND_ALL_USER)
            .data(
                userRepository
                    .findAll(PageRequest.of(page, size))
                    .map(userMapper::userToUserResponseDTO)
            )
            .build();
    }

    @Override
    public ApiResponseDTO<UserResponseDTO> findUserByUserId(String userId) {
        return ApiResponseDTO.<UserResponseDTO>builder()
                .success(true)
                .status(ApiResponseStatus.SUCCESS)
                .message(SUCCESS_FOUND_USER)
                .data(
                    userRepository.findById(userId)
                        .map(userMapper::userToUserResponseDTO)
                        .orElseThrow(()-> new ResourceNotFoundException(ERROR_FOUND_USER))
                )
                .build();
    }

    private String getCurrentUserId() {
        return SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getName();
    }
}
