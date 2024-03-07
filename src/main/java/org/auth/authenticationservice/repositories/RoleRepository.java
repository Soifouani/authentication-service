package org.auth.authenticationservice.repositories;

import org.auth.authenticationservice.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByName(String roleName);
}
