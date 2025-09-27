package co.edu.unimagdalena.libros.libros.service;

import co.edu.unimagdalena.libros.libros.dto.BookDefinitionDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface BookDefinitionService {

    List<BookDefinitionDto> getByTitleAuthorIsbn(String author, String title, String isbn);

    default Optional<BookDefinitionDto> getBookByIsbn(String isbn) {
        return getByTitleAuthorIsbn(null, null, isbn).stream().findFirst();
    }
    default Optional<BookDefinitionDto> getBookByTitle(String title) {
        return getByTitleAuthorIsbn(null, title, null).stream().findFirst();
    }
    default List<BookDefinitionDto> getBooksByAuthor(String author) {
        return getByTitleAuthorIsbn(author, null, null);
    }
    default List<BookDefinitionDto> getBooksByTitleContaining(String title) {
        return getByTitleAuthorIsbn(null, "%" + title + "%", null);
    }

    Optional<BookDefinitionDto> getBookById(UUID id);
    BookDefinitionDto createBook(BookDefinitionDto bookDefinitionDto);
    BookDefinitionDto updateBook(BookDefinitionDto bookDefinitionDto);
    void deleteBookById(UUID id);
    List<BookDefinitionDto> getAllBooks();
}
