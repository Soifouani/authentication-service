package org.auth.authenticationservice.services;

import org.auth.authenticationservice.dtos.ApiResponseDTO;
import org.auth.authenticationservice.dtos.RoleRequestDTO;
import org.auth.authenticationservice.dtos.RoleResponseDTO;
import org.springframework.data.domain.Page;

public interface RoleService {
    ApiResponseDTO<RoleResponseDTO> createRole(RoleRequestDTO roleRequestDTO);

    ApiResponseDTO<RoleResponseDTO> findRoleById(String roleId);

    ApiResponseDTO<Page<RoleResponseDTO>> findAllRoles(int page, int size);
}
