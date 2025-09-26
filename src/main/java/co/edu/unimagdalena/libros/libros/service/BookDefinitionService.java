package co.edu.unimagdalena.libros.libros.service;

import co.edu.unimagdalena.libros.libros.dto.BookDefinitionDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface BookDefinitionService {

    Optional<BookDefinitionDto> getBookByTitle(String title);
    List<BookDefinitionDto> getBooksByAuthor(String author);
    List<BookDefinitionDto> getBooksByTitleContaining(String title);
    Optional<BookDefinitionDto> getBookByIsbn(String isbn);

    Optional<BookDefinitionDto> getBookById(UUID id);
    BookDefinitionDto createBook(BookDefinitionDto bookDefinitionDto);
    BookDefinitionDto updateBook(BookDefinitionDto bookDefinitionDto);
    void deleteBookById(UUID id);
    List<BookDefinitionDto> getAllBooks();
}
