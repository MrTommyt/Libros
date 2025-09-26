package co.edu.unimagdalena.libros.libros.controller;


import co.edu.unimagdalena.libros.libros.Dto.BookDefinitionDto;
import co.edu.unimagdalena.libros.libros.service.BookDefinitionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {
    private final BookDefinitionService bookDefinitionService;


    @GetMapping()
    public ResponseEntity<List<BookDefinitionDto>> getAllBooks(){
        return ResponseEntity.ok(bookDefinitionService.getAllBooks());
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<BookDefinitionDto> getBookByTitle(@PathVariable String title){
        return  ResponseEntity.ok(bookDefinitionService.getBookByTitle(title));
    }

    @PostMapping()
    public ResponseEntity<BookDefinitionDto> createBook(@Valid @RequestBody BookDefinitionDto bookDefinitionDto){
        return ResponseEntity.ok(bookDefinitionService.createBook(bookDefinitionDto));
    }

}
