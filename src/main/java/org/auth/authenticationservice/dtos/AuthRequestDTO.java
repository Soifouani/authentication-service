package org.auth.authenticationservice.dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class AuthRequestDTO {
    String grantType;
    String username;
    String password;
    boolean withRefreshToken;
    String refreshToken;
}
