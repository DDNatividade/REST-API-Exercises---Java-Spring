package com.apis.apisjwtswagger.Security.Service;


import com.apis.apisjwtswagger.Entity.UsersEntity;
import com.apis.apisjwtswagger.Repository.UsersRepository;
import com.apis.apisjwtswagger.Security.AuthenticationDTO.AuthRequest;
import com.apis.apisjwtswagger.Security.AuthenticationDTO.AuthResponse;
import com.apis.apisjwtswagger.Security.AuthenticationDTO.RegisterRequest;
import com.apis.apisjwtswagger.Security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsersRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /**
     * REGISTRO - Crea usuario y devuelve JWT
     */

    public AuthResponse register(RegisterRequest request) {

        // 1. Crear el usuario con password hasheada
        var newUser = UsersEntity.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        // 2. Guardar en BD
        repository.save(newUser);

        // 3. Generar JWT
        var jwtToken = jwtService.generateToken(newUser);

        // 4. Devolver respuesta con el token
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    /**
     * LOGIN - Valida credenciales y devuelve JWT
     */

    public AuthResponse authenticate(AuthRequest request) {
        // 1. AUTENTICAR - Aquí Spring valida user/password
        // Si falla, lanza una excepción automáticamente
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        // 2. Si llegamos aquí, las credenciales son correctas
        // Buscar el usuario en BD
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();

        // 3. Generar JWT
        var jwtToken = jwtService.generateToken(user);

        // 4. Devolver respuesta con el token
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}