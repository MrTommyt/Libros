package co.edu.unimagdalena.libros.libros.service;

import co.edu.unimagdalena.libros.libros.dto.BookDefinitionDto;

import java.util.List;


public interface BookDefinitionService {

    BookDefinitionDto getBookByTitle(String title);
    List<BookDefinitionDto> getBooksByAuthor(String author);
    List<BookDefinitionDto> getBooksByTitleContaining(String title);
    BookDefinitionDto createBook(BookDefinitionDto bookDefinitionDto);
    List<BookDefinitionDto> getAllBooks();
}
