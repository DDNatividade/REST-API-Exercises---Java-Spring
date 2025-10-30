package com.apis.apisjwtswagger.Security.AuthenticationDTO;

import com.apis.apisjwtswagger.Entity.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * REQUEST de registro
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String email;
    private String password;
    private RoleEntity role;
}