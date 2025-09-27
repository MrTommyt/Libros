package co.edu.unimagdalena.libros.libros.service;

import co.edu.unimagdalena.libros.libros.dto.BookDefinitionDto;
import co.edu.unimagdalena.libros.libros.dto.BookDto;
import co.edu.unimagdalena.libros.libros.dto.ClientDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookDto> getAllClientBooks(ClientDto client);
    List<BookDto> getAllBooksByDefinition(BookDefinitionDto bookDefinition);
    List<BookDto> getAllBooks();
    Optional<BookDto> getBookById(Long id);
    BookDto createBook(BookDto bookDto);
    void deleteBookById(Long id);
    BookDto updateBook(BookDto bookDto);
}
