package co.edu.unimagdalena.libros.libros.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import co.edu.unimagdalena.libros.libros.service.imp.BookDefinitionSyncService;

@Component
@RequiredArgsConstructor
public class BookDatabaseInitializer {

    private final BookDefinitionSyncService bookDefinitionSyncService;

    @PostConstruct
    public void init() {
        bookDefinitionSyncService.syncFromGoogle("harry+potter");
        bookDefinitionSyncService.syncFromGoogle("lord+of+the+rings");
        bookDefinitionSyncService.syncFromGoogle("science");
        bookDefinitionSyncService.syncFromGoogle("history");
        System.out.println("Sincronización inicial de libros completada.");
    }
}
