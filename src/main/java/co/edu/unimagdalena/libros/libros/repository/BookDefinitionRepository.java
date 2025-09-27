package co.edu.unimagdalena.libros.libros.repository;

import co.edu.unimagdalena.libros.libros.entity.BookDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface BookDefinitionRepository extends JpaRepository<BookDefinition, UUID> {
    Optional<BookDefinition> findByIsbn(String isbn);
    List<BookDefinition> findByTitleContainingIgnoreCase(String title);
    List<BookDefinition> findByAuthorContainingIgnoreCase(String author);
    List<BookDefinition> findByEditorialContainingIgnoreCase(String editorial);



    @Query("select d from BookDefinition d where d.author like ?1 and d.title like ?2 and d.editorial like ?3")
    List<BookDefinition> findByTitleAuthorEditorial(String author, String title, String editorial);

    default List<BookDefinition> findByEditorial(String editorial) {
        return findByTitleAuthorEditorial("%", "%", editorial).stream().toList();
    }
    default Optional<BookDefinition> findByTitle(String title) {
        return findByTitleAuthorEditorial("%", title, "%").stream().findFirst();
    }
    default List<BookDefinition> findByAuthor(String author) {
        return findByTitleAuthorEditorial(author, "%", "%");
    }
    default List<BookDefinition> findByTitleContains(String title) {
        return findByTitleAuthorEditorial("%", "%" + title + "%", "%");
    }

}
