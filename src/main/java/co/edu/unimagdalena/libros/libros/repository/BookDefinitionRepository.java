package co.edu.unimagdalena.libros.libros.repository;

import co.edu.unimagdalena.libros.libros.entity.BookDefinition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface BookDefinitionRepository extends JpaRepository<BookDefinition, UUID> {
    Optional<BookDefinition> findByIsbn(String isbn);
    Optional<BookDefinition> findByTitle(String title);
    List<BookDefinition> findByAuthor(String author);
    List<BookDefinition> findByTitleContains(String title);
}
