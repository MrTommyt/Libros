package co.edu.unimagdalena.libros.libros.repository;

import co.edu.unimagdalena.libros.libros.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    Optional<Client> findByName(String name);
    Optional<Client> findByEmail(String email);


}
