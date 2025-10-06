package co.edu.unimagdalena.libros.libros.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unimagdalena.libros.libros.config.JwtUtil;
import co.edu.unimagdalena.libros.libros.dto.ClientDto;
import co.edu.unimagdalena.libros.libros.dto.auth.AuthRequestDto;
import co.edu.unimagdalena.libros.libros.dto.auth.AuthResponse;
import co.edu.unimagdalena.libros.libros.dto.auth.UserRegistrationDto;
import co.edu.unimagdalena.libros.libros.service.ClientService;
import co.edu.unimagdalena.libros.libros.service.UserDetailsServiceImpl;

@RestController
@RequestMapping("/auth")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserDetailsServiceImpl service;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final ClientService clientService;

    UserController(UserDetailsServiceImpl service, JwtUtil jwtUtil, AuthenticationManager authenticationManager, ClientService clientService) {
        this.service = service;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.clientService = clientService;
    }

    @PostMapping("/signup")
    public ResponseEntity<ClientDto> addNewUser(@RequestBody UserRegistrationDto userInfo) {
        ClientDto response = service.addUser(userInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticateAndGetToken(@RequestBody AuthRequestDto authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.email(), authRequest.password()));

        if (authentication.isAuthenticated()) {
            String token = jwtUtil.generateJwtToken(authentication);
            log.info("Token: " + token);
            return clientService.getClientByEmail(authRequest.email()).map(client -> {
                return new AuthResponse(token, client.getId());
            }).map(c -> ResponseEntity.accepted().body(c))
            .orElse(ResponseEntity.notFound().build());

        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }
}
