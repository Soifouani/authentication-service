package org.auth.authenticationservice.dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter @Setter @ToString @Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class RoleResponseDTO {
    String roleId;
    String name;
}
