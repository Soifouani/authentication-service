package org.auth.authenticationservice.web;

import lombok.AllArgsConstructor;
import org.auth.authenticationservice.dtos.ApiResponseDTO;
import org.auth.authenticationservice.dtos.RoleRequestDTO;
import org.auth.authenticationservice.dtos.RoleResponseDTO;
import org.auth.authenticationservice.services.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.auth.authenticationservice.constants.Api.SCOPE_ADMIN;

@RestController
@AllArgsConstructor
@RequestMapping("/api/roles")
public class RoleRestAPI {

    private final RoleService roleService;

    @PostMapping()
    @PreAuthorize("hasAuthority('"+ SCOPE_ADMIN +"')")
    public ResponseEntity<ApiResponseDTO<RoleResponseDTO>> addRole(@RequestBody RoleRequestDTO roleRequestDTO) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(roleService.createRole(roleRequestDTO));
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('"+ SCOPE_ADMIN +"')")
    public ResponseEntity<ApiResponseDTO<Page<RoleResponseDTO>>> findAllRoles(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(roleService.findAllRoles(page, size));
    }
}
