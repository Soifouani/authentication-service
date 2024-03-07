package org.auth.authenticationservice.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.auth.authenticationservice.enums.AccountStatus;
import org.auth.authenticationservice.enums.Gender;
import org.hibernate.annotations.GenericGenerator;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter @ToString @Builder
@AllArgsConstructor @NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name ="USER_ID")
    String userId;

    @Column(name = "USERNAME", unique = true)
    String username;

    @Column(name ="PASSWORD")
    String password;

    @Column(name ="FIRSTNAME")
    String firstname;

    @Column(name ="LASTNAME")
    String lastname;

    @Column(name ="GENDER")
    @Enumerated(EnumType.STRING)
    Gender gender;

    @Column(name = "EMAIL", unique = true)
    String email;

    @Column(name ="STATUS")
    @Enumerated(EnumType.STRING)
    AccountStatus status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USERS_ROLES",
        joinColumns = { @JoinColumn(name = "USER_ID") },
        inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") },
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"USER_ID","ROLE_ID"})
        }
    )
    List<Role> roles = new ArrayList<>();

    @Column(name ="PHOTO_URL")
    String photoURL;

    @Column(name ="CREATED_AT")
    Date createdAt;

    @Column(name ="SUSPENDED_AT")
    Date suspendedAt;

    @Column(name ="DEACTIVATED_AT")
    Date deactivatedAt;

    @Column(name ="ACTIVATED_AT")
    Date activatedAt;

    @Column(name ="DATE_OF_BIRTH")
    String dateOfBirth;

    @Column(name ="CITY")
    String city;

    @Column(name ="DESCRIPTION")
    String description;

    @Column(name ="TEMP_ACTIVATION_CODE")
    String temporaryActivationCode;

    @Column(name ="TEMP_ACTIVATION_CODE_TIME_STAMP")
    Instant temporaryActivationCodeTimeStamp;
}
