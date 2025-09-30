package co.edu.unimagdalena.libros.libros.controller;

import co.edu.unimagdalena.libros.libros.dto.AuthenticationResponse;
import co.edu.unimagdalena.libros.libros.dto.LoginRequest;
import co.edu.unimagdalena.libros.libros.dto.SignupRequest;
import co.edu.unimagdalena.libros.libros.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> signup(
            @Valid @RequestBody SignupRequest request
    ) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @Valid @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(authenticationService.login(request));
    }
}