package org.auth.authenticationservice.services.servicesImpl;

import lombok.AllArgsConstructor;
import org.auth.authenticationservice.dtos.ApiResponseDTO;
import org.auth.authenticationservice.dtos.RoleRequestDTO;
import org.auth.authenticationservice.dtos.RoleResponseDTO;
import org.auth.authenticationservice.enums.ApiResponseStatus;
import org.auth.authenticationservice.exceptions.ResourceNotFoundException;
import org.auth.authenticationservice.mappers.RoleMapper;
import org.auth.authenticationservice.repositories.RoleRepository;
import org.auth.authenticationservice.services.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.auth.authenticationservice.constants.MessageKey.*;

@Service
@AllArgsConstructor
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public ApiResponseDTO<RoleResponseDTO> createRole(RoleRequestDTO roleRequestDTO) {
        var role = roleMapper.roleRequestDTOToRole(roleRequestDTO);
        return ApiResponseDTO.<RoleResponseDTO>builder()
            .success(true)
            .status(ApiResponseStatus.SUCCESS)
            .message(SUCCESS_CREATED_ROLE)
            .data(roleMapper.roleToRoleResponseDTO(roleRepository.save(role)))
            .build();
    }

    @Override
    public ApiResponseDTO<RoleResponseDTO> findRoleById(String roleId) {
        return ApiResponseDTO.<RoleResponseDTO>builder()
            .success(true)
            .status(ApiResponseStatus.SUCCESS)
            .message(SUCCESS_FOUND_ROLE)
            .data(roleMapper.roleToRoleResponseDTO(
                roleRepository.findById(roleId).orElseThrow(()-> new ResourceNotFoundException(ERROR_CREATED_ROLE)))
            )
            .build();
    }

    @Override
    public ApiResponseDTO<Page<RoleResponseDTO>> findAllRoles(int page, int size) {
        return ApiResponseDTO.<Page<RoleResponseDTO>>builder()
            .success(true)
            .status(ApiResponseStatus.SUCCESS)
            .message(SUCCESS_FOUND_ALL_ROLE)
            .data(roleRepository.findAll(PageRequest.of(page, size))
                    .map(roleMapper::roleToRoleResponseDTO))
            .build();
    }
}
