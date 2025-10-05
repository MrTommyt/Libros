package co.edu.unimagdalena.libros.libros.service.imp;

import co.edu.unimagdalena.libros.libros.dto.BookDefinitionDto;
import co.edu.unimagdalena.libros.libros.dto.BookDto;
import co.edu.unimagdalena.libros.libros.dto.ClientDto;
import co.edu.unimagdalena.libros.libros.entity.Book;
import co.edu.unimagdalena.libros.libros.entity.BookDefinition;
import co.edu.unimagdalena.libros.libros.entity.Client;
import co.edu.unimagdalena.libros.libros.exeption.BookNotFoundException;
import co.edu.unimagdalena.libros.libros.mapper.BookDefinitionMapper;
import co.edu.unimagdalena.libros.libros.mapper.BookMapper;
import co.edu.unimagdalena.libros.libros.mapper.ClientMapper;
import co.edu.unimagdalena.libros.libros.repository.BookDefinitionRepository;
import co.edu.unimagdalena.libros.libros.repository.BookRepository;
import co.edu.unimagdalena.libros.libros.repository.ClientRepository;
import co.edu.unimagdalena.libros.libros.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final ClientRepository clientRepository;
    private final BookDefinitionRepository bookDefinitionRepository;

    private final BookMapper bookMapper;
    private final ClientMapper clientMapper;
    private final BookDefinitionMapper bookDefinitionMapper;

    @Override
    public List<BookDto> getAllClientBooks(ClientDto client) {
        return bookRepository.findBooksByClient(clientMapper.toEntity(client))
            .stream()
            .map(bookMapper::toDto)
            .toList();
    }

    @Override
    public List<BookDto> getAllBooksByDefinition(BookDefinitionDto bookDefinition) {
        return bookRepository.findBooksByBookDefinition(bookDefinitionMapper.toEntity(bookDefinition))
            .stream()
            .map(bookMapper::toDto)
            .toList();
    }

    @Override
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream().map(bookMapper::toDto).toList();
    }

    @Override

    public BookDto getBookById(UUID id) {
        return bookRepository.findById(id).map(bookMapper::toDto).orElseThrow(() -> new BookNotFoundException("no se encontro el libro"));
    }

    @Override
    public BookDto createBook(BookDto dto) {
        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        BookDefinition bookDefinition = bookDefinitionRepository.findById(dto.getBookDefinitionID())
                .orElseThrow(() -> new RuntimeException("BookDefinition no encontrado"));

        Book book = new Book();
        book.setClient(client);
        book.setBookDefinition(bookDefinition);
        book.setState(dto.getState());

        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public void deleteBookById(UUID id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {
        return bookMapper.toDto(bookRepository.save(bookMapper.toEntity(bookDto)));
    }

    //solo mis libros
    @Override
    public List<BookDto> findBooksByClientId(UUID ClientId) {
        return bookRepository.findByClientIdAndState(ClientId, "PUBLICADO")
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }

    // libros de los demas
    @Override
    public List<BookDto> findBooksFromOthers(UUID myClientId) {
        return bookRepository.findByClientIdNotAndState(myClientId, "PUBLICADO")
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public List<BookDto> findAllPublicBooks() {
        return List.of();
    }
}
