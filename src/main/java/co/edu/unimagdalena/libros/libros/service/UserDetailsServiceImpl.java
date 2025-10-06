package co.edu.unimagdalena.libros.libros.service;


import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import co.edu.unimagdalena.libros.libros.config.service.UserDetailsImp;
import co.edu.unimagdalena.libros.libros.dto.ClientDto;
import co.edu.unimagdalena.libros.libros.dto.UserInfoDto;
import co.edu.unimagdalena.libros.libros.entity.ERole;
import co.edu.unimagdalena.libros.libros.entity.Role;
import co.edu.unimagdalena.libros.libros.entity.User;
import co.edu.unimagdalena.libros.libros.repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

  public UserDetailsServiceImpl(UserRepository userRepository) {
      this.userRepository = userRepository;
      this.passwordEncoder = new BCryptPasswordEncoder();
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Optional<User> userOptional = userRepository.findByUsername(username);

      return userOptional.map(UserDetailsImp::build)
        .orElseThrow(() -> new UsernameNotFoundException("[ERROR]: user by %s not found".formatted(username)));
  }

  public UserInfoDto addUser(ClientDto userInfo) {
      User user = new User(null, userInfo.getName(), userInfo.getEmail(), passwordEncoder.encode(userInfo.getPassword()),
              Arrays.stream(userInfo.roles().split(" "))
                      .map(role -> new Role(ERole.valueOf(role)))
                      .collect(Collectors.toSet())
      );
      user = userRepository.save(user);
      return new UserInfoDto(user.getUsername(), user.getEmail(), user.getPassword(), userInfo.roles());
  }
}
