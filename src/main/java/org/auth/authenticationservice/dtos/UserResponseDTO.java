package org.auth.authenticationservice.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.auth.authenticationservice.enums.AccountStatus;
import org.auth.authenticationservice.enums.Gender;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter @Setter @ToString @Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class UserResponseDTO {
    String userId;

    String username;

    String firstname;

    String lastname;

    Gender gender;

    String email;

    AccountStatus status;

    List<RoleResponseDTO> roles = new ArrayList<>();

    String photoURL;

    String dateOfBirth;

    String city;

    String description;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    Date createdAt;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    Date accountCreated;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    Date suspendedAt;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    Date deactivatedAt;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    Date activatedAt;
}
