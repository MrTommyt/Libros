package co.edu.unimagdalena.libros.libros.controller;


import co.edu.unimagdalena.libros.libros.dto.BookDefinitionDto;
import co.edu.unimagdalena.libros.libros.service.BookDefinitionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/titles")
@CrossOrigin(origins = "http://localhost:3000")
public class BookDefinitionController {
    private final BookDefinitionService bookDefinitionService;

    @GetMapping()
    public ResponseEntity<List<BookDefinitionDto>> getAllBooks(){
        return ResponseEntity.ok(bookDefinitionService.getAllBooks());
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<BookDefinitionDto> getBookByTitle(@PathVariable String title){
        return bookDefinitionService.getBookByTitle(title)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<BookDefinitionDto>> getBooksByAuthor(@PathVariable String author){
        return ResponseEntity.ok(bookDefinitionService.getBooksByAuthor(author));
    }

    @PostMapping()
    public ResponseEntity<BookDefinitionDto> createBook(@Valid @RequestBody BookDefinitionDto bookDefinitionDto){
        return ResponseEntity.ok(bookDefinitionService.createBook(bookDefinitionDto));
    }

}
