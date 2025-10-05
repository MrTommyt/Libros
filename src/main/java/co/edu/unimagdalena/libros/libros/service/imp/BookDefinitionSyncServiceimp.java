package co.edu.unimagdalena.libros.libros.service.imp;

import co.edu.unimagdalena.libros.libros.dto.BookDefinitionDto;
import co.edu.unimagdalena.libros.libros.entity.BookDefinition;
import co.edu.unimagdalena.libros.libros.repository.BookDefinitionRepository;
import co.edu.unimagdalena.libros.libros.service.BookDefinitionSyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookDefinitionSyncServiceimp implements BookDefinitionSyncService {

    private final ExternalBookServiceImp externalBookServiceImp;
    private final BookDefinitionRepository bookDefinitionRepository;

    @Override
    public int syncFromGoogle(String query) {
        List<BookDefinitionDto> externalBooks = externalBookServiceImp.fetchBooksFromGoogle(query);
        int savedCount = 0;

        for (BookDefinitionDto dto : externalBooks) {
            boolean exists = bookDefinitionRepository
                    .findAll()
                    .stream()
                    .anyMatch(b -> b.getIsbn().equalsIgnoreCase(dto.getIsbn()));

            if (!exists) {
                BookDefinition entity = new BookDefinition();
                entity.setTitle(dto.getTitle());
                entity.setAuthor(dto.getAuthor());
                entity.setEditorial(dto.getEditorial());
                entity.setIsbn(dto.getIsbn());
                bookDefinitionRepository.save(entity);
                savedCount++;
            }
        }

        return savedCount;
    }
}
