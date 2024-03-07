package org.auth.authenticationservice.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter @Setter @ToString @Builder
@AllArgsConstructor @NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Table(name = "ROLE")
public class Role {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name ="ROLE_ID")
    String roleId;

    @Column(name = "NAME", unique = true)
    String name;
}
