package co.edu.unimagdalena.libros.libros.service.imp;

import co.edu.unimagdalena.libros.libros.dto.LoginRequestDto;
import co.edu.unimagdalena.libros.libros.dto.LoginResponseDto;
import co.edu.unimagdalena.libros.libros.dto.RegisterRequestDto;
import co.edu.unimagdalena.libros.libros.dto.ClientDto;
import co.edu.unimagdalena.libros.libros.entity.Client;
import co.edu.unimagdalena.libros.libros.mapper.ClientMapper;
import co.edu.unimagdalena.libros.libros.repository.ClientRepository;
import co.edu.unimagdalena.libros.libros.security.JwtService;
import co.edu.unimagdalena.libros.libros.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ClientMapper clientMapper;

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequest) {
        // Autentica las credenciales
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        // Busca el usuario en la base de datos
        Client client = clientRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Genera el token JWT
        String jwtToken = jwtService.generateToken(client.getEmail(), client.getId());

        // Retorna la respuesta con token y datos del usuario
        return new LoginResponseDto(jwtToken, clientMapper.toDto(client));
    }

    @Override
    public ClientDto register(RegisterRequestDto registerRequest) {
        // Verifica que no exista el email
        if (clientRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new RuntimeException("Email ya registrado");
        }

        // Crea el nuevo cliente
        Client client = new Client();
        client.setName(registerRequest.getName());
        client.setEmail(registerRequest.getEmail());
        client.setAddress(registerRequest.getAddress());
        client.setPassword(passwordEncoder.encode(registerRequest.getPassword())); // Encripta la contraseña

        // Guarda en la base de datos
        Client savedClient = clientRepository.save(client);

        return clientMapper.toDto(savedClient);
    }
}
