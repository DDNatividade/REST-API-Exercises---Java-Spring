package com.apis.apisjwtswagger.Security;
import com.apis.apisjwtswagger.DTO.Users.UserCreateDTO;
import com.apis.apisjwtswagger.DTO.Users.UserLoginDTO;
import com.apis.apisjwtswagger.Security.AuthenticationDTO.AuthResponse;
import com.apis.apisjwtswagger.Security.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apis/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody @Valid UserCreateDTO request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserLoginDTO request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
