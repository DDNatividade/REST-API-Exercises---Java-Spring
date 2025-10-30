package com.apis.apisjwtswagger.Security.AuthenticationDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RESPONSE con el JWT
 * Esto es lo que devuelves al usuario después de login exitoso
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String token;
}

