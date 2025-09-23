package co.edu.unimagdalena.libros.libros.repository;

import co.edu.unimagdalena.libros.libros.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
    @Override
    List<Book> findAll();

    @Override
    Optional<Book> findById(UUID id);
}
