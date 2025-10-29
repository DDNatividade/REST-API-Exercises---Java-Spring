package com.apis.apisjwtswagger.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // No hay token, dejamos que siga (puede ser ruta pública)
            filterChain.doFilter(request, response);
            return;
        }

        // EXTRAER el token (quitamos "Bearer " del principio)
        jwt = authHeader.substring(7);

        userEmail = jwtService.extractUsername(jwt);

        // VALIDAMOS que:
        //    - Tenemos un username
        //    - NO hay ya una autenticación en el contexto (no está ya autenticado)

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // CARGAR los detalles del usuario desde la BD
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
        }
    }
}