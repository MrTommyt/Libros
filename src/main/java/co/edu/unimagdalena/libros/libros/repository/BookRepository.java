package co.edu.unimagdalena.libros.libros.repository;

import co.edu.unimagdalena.libros.libros.entity.Book;
import co.edu.unimagdalena.libros.libros.entity.BookDefinition;
import co.edu.unimagdalena.libros.libros.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
    List<Book> findBooksByBookDefinition(BookDefinition bookDefinition);
    List<Book> findBooksByClient(Client client);


}
