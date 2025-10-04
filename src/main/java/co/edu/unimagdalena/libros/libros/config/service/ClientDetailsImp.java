package co.edu.unimagdalena.libros.libros.config.service;

import co.edu.unimagdalena.libros.libros.entity.Client;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ClientDetailsImp implements UserDetails {
    private UUID id;
    private String name;
    private String address;
    private String email;
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public ClientDetailsImp(UUID id, String name, String address, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static ClientDetailsImp build(Client user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
        return new ClientDetailsImp(user.getId(),
                user.getName(),
                user.getAddress(),
                user.getEmail(),
                user.getPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public UUID getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

}
