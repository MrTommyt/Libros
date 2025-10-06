package co.edu.unimagdalena.libros.libros.controller;

import co.edu.unimagdalena.libros.libros.dto.BookDto;
import co.edu.unimagdalena.libros.libros.dto.ClientDto;
import co.edu.unimagdalena.libros.libros.service.BookService;
import co.edu.unimagdalena.libros.libros.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/clients")
@CrossOrigin(origins = "http://localhost:3000")
public class ClientController {
    private final ClientService clientService;
    private final BookService bookService;

    @GetMapping()
    public ResponseEntity<List<ClientDto>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable("id") UUID id) {
        return clientService.getClientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/")
    public ResponseEntity<ClientDto> updateClient(@Valid @RequestBody ClientDto clientDto) {
        return ResponseEntity.ok(clientService.updateClient(clientDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable("id") UUID id) {
        clientService.deleteClientById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<ClientDto> createClient(@Valid @RequestBody ClientDto clientDto) {
        return ResponseEntity.ok(clientService.createClient(clientDto));
    }

    @GetMapping("/{id}/books")
    public ResponseEntity<List<BookDto>> getBooksByClientId(@PathVariable("id") UUID id){
        return ResponseEntity.ok(bookService.findBooksByClientId(id));
    }

}
