package co.edu.unimagdalena.libros.libros.service.imp;

import co.edu.unimagdalena.libros.libros.dto.BookDefinitionDto;
import co.edu.unimagdalena.libros.libros.entity.BookDefinition;
import co.edu.unimagdalena.libros.libros.exeption.BookNotFoundException;
import co.edu.unimagdalena.libros.libros.mapper.BookDefinitionMapper;
import co.edu.unimagdalena.libros.libros.repository.BookDefinitionRepository;
import co.edu.unimagdalena.libros.libros.service.BookDefinitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookDefinitionServiceImp implements BookDefinitionService {
    private final BookDefinitionRepository bookDefinitionRepository;
    private final BookDefinitionMapper bookDefinitionMapper;

    @Override
    public List<BookDefinitionDto> getAllBooks() {
        return bookDefinitionRepository.findAll().stream().map(bookDefinitionMapper::toDto).toList();
    }

    @Override
    public List<BookDefinition> getbookbytitlecontains(String title) {
        return List.of();
    }

    @Override
    public List<BookDefinitionDto> getByTitleAuthorIsbn(String author, String title, String editorial) {
        return bookDefinitionRepository.findAll().stream()
                .filter(book -> (author == null || book.getAuthor().toLowerCase().contains(author.toLowerCase())) &&
                        (title == null || book.getTitle().toLowerCase().contains(title.toLowerCase())) &&
                        (editorial == null || book.getEditorial().toLowerCase().contains(editorial.toLowerCase())))
                .map(bookDefinitionMapper::toDto)
                .toList();
    }

    @Override
    public BookDefinitionDto getBookById(UUID id) {
        return bookDefinitionRepository.findById(id)
                .map(bookDefinitionMapper::toDto)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));
    }

    @Override
    public BookDefinitionDto createBook(BookDefinitionDto bookDefinitionDto){
        BookDefinition bookDefinition = bookDefinitionMapper.toEntity(bookDefinitionDto);
        return bookDefinitionMapper.toDto(bookDefinitionRepository.save(bookDefinition));
    }

    @Override
    public BookDefinitionDto updateBook(BookDefinitionDto bookDefinitionDto) {
        return bookDefinitionMapper.toDto(bookDefinitionRepository.save(bookDefinitionMapper.toEntity(bookDefinitionDto)));
    }

    @Override
    public void deleteBookById(UUID id) {
        bookDefinitionRepository.deleteById(id);
    }




}
