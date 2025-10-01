package co.edu.unimagdalena.libros.libros.controller;

import co.edu.unimagdalena.libros.libros.dto.BookDto;
import co.edu.unimagdalena.libros.libros.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {
    private final BookService bookService;
    
    @GetMapping()
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("id") UUID id){
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PutMapping("/")
    public ResponseEntity<BookDto> updateBook(@Valid @RequestBody BookDto bookDto){
        return ResponseEntity.ok(bookService.updateBook(bookDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable("id") UUID id){
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto bookDto){
        return ResponseEntity.ok(bookService.createBook(bookDto));
    }

}
