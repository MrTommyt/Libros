package co.edu.unimagdalena.libros.libros.service;

import co.edu.unimagdalena.libros.libros.Dto.BookDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookService {
    Optional<BookDto> findAll();
    List<BookDto> save(BookDto bookDto);
    List<BookDto> update(UUID id, BookDto newBookDto);
    void delete(BookDto bookDto);
}
