package co.edu.unimagdalena.libros.libros.controller;


import co.edu.unimagdalena.libros.libros.Dto.BookDto;
import co.edu.unimagdalena.libros.libros.entity.Book;
import co.edu.unimagdalena.libros.libros.repository.BookRepository;
import co.edu.unimagdalena.libros.libros.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/Books")
public class BookController {
    private final BookService bookService;


    @GetMapping()
    public ResponseEntity<List<BookDto>> getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<BookDto> getBookByTitle(@PathVariable String titulo){
        return  ResponseEntity.ok(bookService.getBookByTitle(titulo));
    }

    @PostMapping()
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto bookDto){
        return ResponseEntity.ok(bookService.createBook(bookDto));
    }

}
