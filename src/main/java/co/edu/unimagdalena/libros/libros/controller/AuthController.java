package co.edu.unimagdalena.libros.libros.controller;

import co.edu.unimagdalena.libros.libros.dto.LoginRequestDto;
import co.edu.unimagdalena.libros.libros.dto.LoginResponseDto;
import co.edu.unimagdalena.libros.libros.dto.RegisterRequestDto;
import co.edu.unimagdalena.libros.libros.dto.ClientDto;
import co.edu.unimagdalena.libros.libros.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<ClientDto> register(@RequestBody RegisterRequestDto registerRequest) {
        return ResponseEntity.ok(authService.register(registerRequest));
    }
}
