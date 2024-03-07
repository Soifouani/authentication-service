package org.auth.authenticationservice.dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.auth.authenticationservice.enums.ApiResponseStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class ApiResponseDTO<T> {
    boolean success;

    ApiResponseStatus status;

    String message;

    T data;
}
