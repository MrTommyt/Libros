package co.edu.unimagdalena.libros.libros.config.service;

import co.edu.unimagdalena.libros.libros.dto.ClientInfoDto;
import co.edu.unimagdalena.libros.libros.entity.Client;
import co.edu.unimagdalena.libros.libros.entity.ERole;
import co.edu.unimagdalena.libros.libros.entity.Role;
import co.edu.unimagdalena.libros.libros.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientDetailsServiceImpl implements UserDetailsService {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    private static final Logger log = LoggerFactory.getLogger(ClientDetailsServiceImpl.class);

    public ClientDetailsServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Client> clientOptional = clientRepository.findByEmail(email);

        if (clientOptional.isEmpty()) {
            throw new UsernameNotFoundException("[ERROR]: is Empty");
        }
        return ClientDetailsImp.build(clientOptional.get());
    }

    public ClientInfoDto addUser(ClientInfoDto clientInfo) {
        Client client = new Client(null, clientInfo.name(), clientInfo.address(), clientInfo.email(), passwordEncoder.encode(clientInfo.password()),
                Arrays.stream(clientInfo.roles().split(" "))
                        .map(role -> new Role(ERole.valueOf(role)))
                        .collect(Collectors.toSet())
        );
        client = clientRepository.save(client);
        return new ClientInfoDto(client.getName(), client.getAddress(), client.getEmail(), clientInfo.password(), clientInfo.roles());
    }
}
