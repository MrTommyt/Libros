package co.edu.unimagdalena.libros.libros.controller;

import co.edu.unimagdalena.libros.libros.dto.BookDto;
import co.edu.unimagdalena.libros.libros.entity.Book;
import co.edu.unimagdalena.libros.libros.security.JwtService;
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

    private final JwtService jwtService;

    //esto es para mostrar todos los libros publicados para un home sin login
    @GetMapping("/all")
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

    //libros publicados por el usuario actual
    @GetMapping("/mine")
    public ResponseEntity<List<BookDto>> getMyBooks(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        final String prefix = "Bearer ";
        if (!authorizationHeader.startsWith(prefix)) {
            throw new RuntimeException("Authorization header no válido");
        }
        String token = authorizationHeader.substring(prefix.length());

        UUID clientId = jwtService.extractClientId(token);
        return ResponseEntity.ok(bookService.findBooksByClientId(clientId));
    }

    // Libros de los demás (cuando el usuario está logueado)
    @GetMapping("/others")
    public ResponseEntity<List<BookDto>> getBooksFromOthers(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        final String prefix = "Bearer ";
        if (!authorizationHeader.startsWith(prefix)) {
            throw new RuntimeException("Authorization header no válido");
        }
        String token = authorizationHeader.substring(prefix.length());
        UUID clientId = jwtService.extractClientId(token);
        return ResponseEntity.ok(bookService.findBooksFromOthers(clientId));
    }





}
