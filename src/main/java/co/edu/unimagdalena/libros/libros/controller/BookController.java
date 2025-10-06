package co.edu.unimagdalena.libros.libros.controller;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unimagdalena.libros.libros.config.JwtUtil;
import co.edu.unimagdalena.libros.libros.dto.BookDto;
import co.edu.unimagdalena.libros.libros.service.BookService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/books")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {
    private final BookService bookService;
    private final JwtUtil jwtUtil;

    //esto es para mostrar todos los libros publicados para un home sin login
    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    public BookController(BookService bookService, JwtUtil jwtUtil) {
        this.bookService = bookService;
        this.jwtUtil = jwtUtil;
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("id") UUID id){
        log.info("Consultando libros...");
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
        String token = authorizationHeader.substring("Bearer ".length());
        UUID clientId = jwtUtil.getIdFromJwtToken(token);
        return ResponseEntity.ok(bookService.findBooksByClientId(clientId));
    }

    // Libros de los demás (cuando el usuario está logueado)
    @GetMapping("/others")
    public ResponseEntity<List<BookDto>> getBooksFromOthers(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        String token = authorizationHeader.substring("Bearer ".length());
        UUID clientId = jwtUtil.getIdFromJwtToken(token);
        return ResponseEntity.ok(bookService.findBooksFromOthers(clientId));
    }
}
