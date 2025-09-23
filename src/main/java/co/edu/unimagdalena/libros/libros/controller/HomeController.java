package co.edu.unimagdalena.libros.libros.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController("/")
public class HomeController {
    @GetMapping("")
    public ResponseEntity<Void> index() {
        return ResponseEntity.status(HttpStatus.FOUND)
            .location(URI.create("http://localhost:3000/"))
            .build();
    }
}
