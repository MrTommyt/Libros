package co.edu.unimagdalena.libros.libros.controller;


import co.edu.unimagdalena.libros.libros.dto.BookDefinitionDto;
import co.edu.unimagdalena.libros.libros.service.BookDefinitionService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/")
    public ResponseEntity<List<BookDefinitionDto>> getBooks(
        @PathParam("title") String title,
        @PathParam("author") String author,
        @PathParam("editorial") String editorial
    ){
        return ResponseEntity.ok(bookDefinitionService.getByTitleAuthorIsbn(author, title, editorial));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDefinitionDto> getBookById(@PathVariable("id") UUID id){
        return ResponseEntity.ok(bookDefinitionService.getBookById(id));
    }

    @PutMapping("/")
    public ResponseEntity<BookDefinitionDto> updateBook(@Valid @RequestBody BookDefinitionDto bookDefinitionDto){
        return ResponseEntity.ok(bookDefinitionService.updateBook(bookDefinitionDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable("id") UUID id){
        bookDefinitionService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<BookDefinitionDto> createBook(@Valid @RequestBody BookDefinitionDto bookDefinitionDto){
        return ResponseEntity.ok(bookDefinitionService.createBook(bookDefinitionDto));
    }

}
