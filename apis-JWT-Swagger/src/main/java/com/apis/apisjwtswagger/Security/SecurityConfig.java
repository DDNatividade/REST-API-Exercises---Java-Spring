package com.apis.apisjwtswagger.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final UserDetailsService userDetailsService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Desactivamos CSRF porque con JWT no lo necesitas (no usas cookies)
                .csrf(csrf -> csrf.disable())
                // Aquí defines las REGLAS de acceso
                .authorizeHttpRequests(auth -> auth

                        // Rutas PÚBLICAS - cualquiera puede acceder sin token
                        .requestMatchers("/api/auth/**").permitAll()

                        // Rutas PROTEGIDAS - necesitas token válido
                        .anyRequest().authenticated()
                )

                // STATELESS = no guardamos sesión en servidor, todo va en el JWT
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // Inyectamos nuestro AuthenticationProvider (quien valida user/pass)
                .authenticationProvider(authenticationProvider())

                // CRÍTICO: Añadimos nuestro filtro JWT ANTES del filtro de usuario/password
                // Así interceptamos PRIMERO y validamos el token

                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }




    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService); // De dónde sacamos usuarios
        provider.setPasswordEncoder(passwordEncoder());      // Cómo comparamos passwords
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

