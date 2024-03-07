package org.auth.authenticationservice.dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.auth.authenticationservice.enums.Gender;

@Getter @Setter @ToString @Builder
@AllArgsConstructor @NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class UserRequestDTO {

    String username;

    String firstname;

    String lastname;

    String email;

    String password;

    String confirmPassword;

    Gender gender;

    String dateOfBirth;

    String city;

    String description;

    String photoURL;
}
