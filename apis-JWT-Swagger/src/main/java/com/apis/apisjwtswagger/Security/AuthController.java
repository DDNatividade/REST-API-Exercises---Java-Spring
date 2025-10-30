package com.apis.apisjwtswagger.Security;
import com.apis.apisjwtswagger.Security.AuthenticationDTO.AuthRequest;
import com.apis.apisjwtswagger.Security.AuthenticationDTO.AuthResponse;
import com.apis.apisjwtswagger.Security.AuthenticationDTO.RegisterRequest;
import com.apis.apisjwtswagger.Security.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apis/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
