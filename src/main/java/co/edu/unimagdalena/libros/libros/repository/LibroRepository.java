package co.edu.unimagdalena.libros.libros.repository;

import co.edu.unimagdalena.libros.libros.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LibroRepository extends JpaRepository<Libro, UUID> {
    @Override
    List<Libro> findAll();

    @Override
    Optional<Libro> findById(UUID id);
}
