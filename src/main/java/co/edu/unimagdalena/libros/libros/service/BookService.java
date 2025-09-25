package co.edu.unimagdalena.libros.libros.service;

import co.edu.unimagdalena.libros.libros.Dto.BookDto;
import co.edu.unimagdalena.libros.libros.entity.Book;

import java.util.List;
import java.util.Optional;


public interface BookService {

    BookDto getBookByTitle(String title);
    List<BookDto> getBooksByAuthor(String author);
    List<BookDto> getBooksByTitleContaining(String title);
    BookDto createBook(BookDto bookDto);
    List<BookDto> getAllBooks();
}
