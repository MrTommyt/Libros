package co.edu.unimagdalena.libros.libros.service;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.edu.unimagdalena.libros.libros.config.service.UserDetailsImp;
import co.edu.unimagdalena.libros.libros.dto.BookDefinitionDto;
import co.edu.unimagdalena.libros.libros.dto.ClientDto;
import co.edu.unimagdalena.libros.libros.dto.auth.UserRegistrationDto;
import co.edu.unimagdalena.libros.libros.entity.Client;
import co.edu.unimagdalena.libros.libros.entity.User;
import co.edu.unimagdalena.libros.libros.repository.ClientRepository;
import co.edu.unimagdalena.libros.libros.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserRepository userRepository;
  private final ClientRepository clientRepository;
  private final PasswordEncoder passwordEncoder;

  private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

  public UserDetailsServiceImpl(UserRepository userRepository, ClientRepository clientRepository) {
      this.userRepository = userRepository;
      this.clientRepository = clientRepository;
      this.passwordEncoder = new BCryptPasswordEncoder();
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Optional<User> userOptional = userRepository.findByUsername(username);

      return userOptional.map(UserDetailsImp::build)
        .orElseThrow(() -> new UsernameNotFoundException("[ERROR]: user by %s not found".formatted(username)));
  }

  public ClientDto addUser(UserRegistrationDto userInfo) {
    Client client = new Client(null, userInfo.name(), userInfo.address(), userInfo.email(), userInfo.password(), new HashSet<>());
    User user = new User(null, 
        // userInfo.name(),
        userInfo.email(), // the username inside the UserDetails is the email
        passwordEncoder.encode(userInfo.password()), 
        new HashSet<>()
        // TODO: Implement the roles
        // Arrays.stream(userInfo.roles().split(" "))
        //         .map(role -> new Role(ERole.valueOf(role)))
        //         .collect(Collectors.toSet())
    );

    client = clientRepository.save(client);
    user = userRepository.save(user);
    return new ClientDto(client.getId(), client.getName(), client.getAddress(), client.getEmail(), new ArrayList<BookDefinitionDto>());
  }
}
