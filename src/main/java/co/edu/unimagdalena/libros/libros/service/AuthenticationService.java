package co.edu.unimagdalena.libros.libros.service;

import co.edu.unimagdalena.libros.libros.config.JwtUtils;
import co.edu.unimagdalena.libros.libros.dto.AuthenticationResponse;
import co.edu.unimagdalena.libros.libros.dto.LoginRequest;
import co.edu.unimagdalena.libros.libros.dto.SignupRequest;
import co.edu.unimagdalena.libros.libros.entity.Client;
import co.edu.unimagdalena.libros.libros.entity.Role;
import co.edu.unimagdalena.libros.libros.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse signup(SignupRequest request) {
        // Check if user already exists
        if (clientRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("El usuario con este email ya existe");
        }

        // Create new client
        var client = new Client();
        client.setName(request.getName());
        client.setAddress(request.getAddress());
        client.setEmail(request.getEmail());
        client.setPassword(passwordEncoder.encode(request.getPassword()));
        client.setRole(Role.USER);

        // Save client
        clientRepository.save(client);

        // Generate JWT token
        var jwtToken = jwtUtils.generateToken(client);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userId(client.getId())
                .email(client.getEmail())
                .name(client.getName())
                .build();
    }

    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var client = clientRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        var jwtToken = jwtUtils.generateToken(client);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userId(client.getId())
                .email(client.getEmail())
                .name(client.getName())
                .build();
    }
}