package org.auth.authenticationservice.mappers;

import org.auth.authenticationservice.dtos.UserRequestDTO;
import org.auth.authenticationservice.dtos.UserResponseDTO;
import org.auth.authenticationservice.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDTO userToUserResponseDTO(User user);
    User userRequestDTOToUser(UserRequestDTO userRequestDTO);

    User userResponseDTOToUser(UserResponseDTO userResponseDTO);
}
