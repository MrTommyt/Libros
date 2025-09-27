package co.edu.unimagdalena.libros.libros.service;

import co.edu.unimagdalena.libros.libros.dto.BookDefinitionDto;
import co.edu.unimagdalena.libros.libros.entity.BookDefinition;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface BookDefinitionService {

    List<BookDefinitionDto> getByTitleAuthorIsbn(String author, String title, String isbn);


    BookDefinitionDto getBookById(UUID id);
    BookDefinitionDto createBook(BookDefinitionDto bookDefinitionDto);
    BookDefinitionDto updateBook(BookDefinitionDto bookDefinitionDto);
    void deleteBookById(UUID id);
    List<BookDefinitionDto> getAllBooks();

    List<BookDefinition> getbookbytitlecontains(String title);


}
