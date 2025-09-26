package co.edu.unimagdalena.libros.libros.service.imp;

import co.edu.unimagdalena.libros.libros.dto.BookDefinitionDto;
import co.edu.unimagdalena.libros.libros.entity.BookDefinition;
import co.edu.unimagdalena.libros.libros.exeption.BookNotFoundExeption;
import co.edu.unimagdalena.libros.libros.mapper.BookMapper;
import co.edu.unimagdalena.libros.libros.repository.BookDefinitionRepository;
import co.edu.unimagdalena.libros.libros.service.BookDefinitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookDefinitionServiceImp implements BookDefinitionService {
    private final BookDefinitionRepository bookDefinitionRepository;
    private final BookMapper bookMapper;

    @Override
    public List<BookDefinitionDto> getAllBooks() {
        return bookDefinitionRepository.findAll().stream().map(bookMapper::toDto).toList();
    }

    @Override
    public BookDefinitionDto createBook(BookDefinitionDto bookDefinitionDto){
        BookDefinition bookDefinition = bookMapper.toEntity(bookDefinitionDto);
        return bookMapper.toDto(bookDefinitionRepository.save(bookDefinition));
    }

    @Override
    public BookDefinitionDto getBookByTitle(String title) {
        return bookDefinitionRepository.findByTitle(title).
                map(bookMapper::toDto).
                orElseThrow(() -> new BookNotFoundExeption("Book not found by title:" + title));
    }

    @Override
    public List<BookDefinitionDto> getBooksByAuthor(String author) {

        List<BookDefinition> bookDefinitions = bookDefinitionRepository.findByAuthor(author);
        if (bookDefinitions.isEmpty()) {
            throw new BookNotFoundExeption("Book not found by author:" + author);
        }
        return bookDefinitions.stream().
                map(bookMapper::toDto).
                toList();
    }

    @Override
    public List<BookDefinitionDto> getBooksByTitleContaining(String title) {
        List<BookDefinition> bookDefinitionWithTitle = bookDefinitionRepository.findByTitleContains(title);
        if(bookDefinitionWithTitle.isEmpty()) {
            throw new BookNotFoundExeption("Books not found by title:" + title);
        }
        return bookDefinitionWithTitle.
                stream().
                map(bookMapper::toDto).
                toList();
    }
}
