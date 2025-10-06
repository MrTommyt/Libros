package co.edu.unimagdalena.libros.libros.service;

import co.edu.unimagdalena.libros.libros.dto.BookDefinitionDto;
import co.edu.unimagdalena.libros.libros.dto.BookDto;
import co.edu.unimagdalena.libros.libros.dto.ClientDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookService {
    List<BookDto> getAllClientBooks(ClientDto client);
    List<BookDto> getAllBooksByDefinition(BookDefinitionDto bookDefinition);
    List<BookDto> getAllBooks();

    BookDto getBookById(UUID id);

    BookDto createBook(BookDto bookDto);
    void deleteBookById(UUID id);
    BookDto updateBook(BookDto bookDto);

    List<BookDto> findBooksByClientId(UUID MyClientId);
    List<BookDto> findBooksFromOthers(UUID clientId);
    List<BookDto> findAllPublicBooks();

}
