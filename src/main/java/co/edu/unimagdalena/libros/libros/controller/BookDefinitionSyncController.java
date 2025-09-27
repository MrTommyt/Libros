package co.edu.unimagdalena.libros.libros.controller;


import co.edu.unimagdalena.libros.libros.service.imp.BookDefinitionSyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sync-books")
@CrossOrigin(origins = "http://localhost:3000")
public class BookDefinitionSyncController {

    private final BookDefinitionSyncService bookDefinitionSyncService;

    @PostMapping
    public ResponseEntity<String> syncBooks(@RequestParam String query) {
        int count = bookDefinitionSyncService.syncFromGoogle(query);
        return ResponseEntity.ok("Sincronización completada. Libros nuevos agregados: " + count);
    }
}
