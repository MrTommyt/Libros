package co.edu.unimagdalena.libros.libros.service.imp;

import co.edu.unimagdalena.libros.libros.Dto.BookDto;
import co.edu.unimagdalena.libros.libros.entity.Book;
import co.edu.unimagdalena.libros.libros.exeption.BookNotFoundExeption;
import co.edu.unimagdalena.libros.libros.mapper.BookMapper;
import co.edu.unimagdalena.libros.libros.repository.BookRepository;
import co.edu.unimagdalena.libros.libros.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImp implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream().map(bookMapper::toDto).toList();
    }

    @Override
    public BookDto createBook(BookDto bookDto){
        Book book = bookMapper.toEntity(bookDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public BookDto getBookByTitle(String title) {
        return bookRepository.findByTitle(title).
                map(bookMapper::toDto).
                orElseThrow(() -> new BookNotFoundExeption("Book not found by title:" + title));
    }

    @Override
    public List<BookDto> getBooksByAuthor(String author) {

        List<Book> books = bookRepository.findByAuthor(author);
        if (books.isEmpty()) {
            throw new BookNotFoundExeption("Book not found by author:" + author);
        }
        return books.stream().
                map(bookMapper::toDto).
                toList();
    }

    @Override
    public List<BookDto> getBooksByTitleContaining(String title) {
        List<Book> bookWithTitle = bookRepository.findByTitleContains(title);
        if(bookWithTitle.isEmpty()) {
            throw new BookNotFoundExeption("Books not found by title:" + title);
        }
        return bookWithTitle.
                stream().
                map(bookMapper::toDto).
                toList();
    }
}
