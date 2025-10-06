package co.edu.unimagdalena.libros.libros.config.service;

import co.edu.unimagdalena.libros.libros.dto.UserInfoDto;
import co.edu.unimagdalena.libros.libros.entity.Client;
import co.edu.unimagdalena.libros.libros.entity.ERole;
import co.edu.unimagdalena.libros.libros.entity.Role;
import co.edu.unimagdalena.libros.libros.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    public UserDetailsServiceImpl(ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Client> clientOptional = clientRepository.findByEmail(email);
        if (clientOptional.isEmpty()) throw new UsernameNotFoundException("[ERROR]: is Empty");
        return UserDetailsImp.build(clientOptional.get());
    }

    public UserInfoDto addUser(UserInfoDto userInfo) {
        Client client = new Client(
                null,
                userInfo.name(),
                null,
                userInfo.email(),
                passwordEncoder.encode(userInfo.password()),
                null, // books
                Arrays.stream(userInfo.roles().split(" "))
                        .map(role -> new Role(ERole.valueOf(role)))
                        .collect(Collectors.toSet())
        );
        clientRepository.save(client);
        // No devuelvas la contraseña hasheada
        return new UserInfoDto(client.getName(), client.getEmail(), userInfo.password(), userInfo.roles());
    }
}
