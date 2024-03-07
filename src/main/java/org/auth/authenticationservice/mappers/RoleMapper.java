package org.auth.authenticationservice.mappers;

import org.auth.authenticationservice.dtos.RoleRequestDTO;
import org.auth.authenticationservice.dtos.RoleResponseDTO;
import org.auth.authenticationservice.entities.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleResponseDTO roleToRoleResponseDTO(Role role);
    Role roleRequestDTOToRole(RoleRequestDTO roleRequestDTO );
}
