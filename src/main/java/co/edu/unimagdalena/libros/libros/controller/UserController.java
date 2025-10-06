package co.edu.unimagdalena.libros.libros.controller;

import co.edu.unimagdalena.libros.libros.config.JwtUtil;
import co.edu.unimagdalena.libros.libros.config.service.UserDetailsServiceImpl;
import co.edu.unimagdalena.libros.libros.dto.UserInfoDto;
import co.edu.unimagdalena.libros.libros.dto.auth.AuthRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserDetailsServiceImpl service;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    UserController(UserDetailsServiceImpl service, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.service = service;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserInfoDto> addNewUser(@RequestBody UserInfoDto userInfo) {
        log.info("New user request: " + userInfo);
        UserInfoDto response = service.addUser(userInfo);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userInfo.email(), userInfo.password())
        );
        String token = jwtUtil.generateJwtToken(authentication);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Authorization", "Bearer " + token)
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticateAndGetToken(@RequestBody AuthRequestDto authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.email(), authRequest.password()));

        if (authentication.isAuthenticated()) {
            String token = jwtUtil.generateJwtToken(authentication);
            log.info("Token: " + token);
            return ResponseEntity.ok()
                    .header("Authorization", "Bearer " + token)
                    .body(token);

        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }
}
