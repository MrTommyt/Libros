package co.edu.unimagdalena.libros.libros.service;

import co.edu.unimagdalena.libros.libros.dto.BookDefinitionDto;

import java.util.List;

public interface ExternalBookService {
    List<BookDefinitionDto> fetchBooksFromGoogle(String query);
}
