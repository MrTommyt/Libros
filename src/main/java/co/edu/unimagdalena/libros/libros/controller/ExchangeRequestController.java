package co.edu.unimagdalena.libros.libros.controller;


import co.edu.unimagdalena.libros.libros.dto.BookDto;
import co.edu.unimagdalena.libros.libros.dto.ClientDto;
import co.edu.unimagdalena.libros.libros.dto.CreateExchangeRequestDto;
import co.edu.unimagdalena.libros.libros.dto.ExchangeRequestDto;
import co.edu.unimagdalena.libros.libros.enums.ExchangeRequestStatus;
import co.edu.unimagdalena.libros.libros.mapper.BookMapper;
import co.edu.unimagdalena.libros.libros.mapper.ClientMapper;
import co.edu.unimagdalena.libros.libros.repository.BookRepository;
import co.edu.unimagdalena.libros.libros.repository.ClientRepository;
import co.edu.unimagdalena.libros.libros.security.JwtService;
import co.edu.unimagdalena.libros.libros.service.imp.ExchangeRequestServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/exchange")
@CrossOrigin(origins = "http://localhost:3000")
public class ExchangeRequestController {
    @Autowired
    private ExchangeRequestServiceImp exchangeRequestServiceImp;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private BookMapper bookMapper  ;
    @Autowired
    private ClientMapper clientMapper ;
    @Autowired
    private JwtService jwtService; // Tu servicio para extraer datos de JWT

    @PostMapping
    public ResponseEntity<ExchangeRequestDto> createExchange(
            @RequestBody CreateExchangeRequestDto dto,
            @RequestHeader("Authorization") String authorizationHeader
    ) throws Exception {
        System.out.println("Authorization header received: " + authorizationHeader);

        final String prefix = "Bearer ";
        if (!authorizationHeader.startsWith(prefix)) throw new RuntimeException("Header inválido");
        String token = authorizationHeader.substring(prefix.length());
        UUID fromUserId = jwtService.extractClientId(token);

        BookDto bookOffered = bookMapper.toDto(
                bookRepository.findById(dto.getBookOfferedId()).orElseThrow(() -> new Exception("Book offered not found"))
        );
        BookDto bookRequested = bookMapper.toDto(
                bookRepository.findById(dto.getBookRequestId()).orElseThrow(() -> new Exception("Book requested not found"))
        );
        ClientDto fromUser = clientMapper.toDto(
                clientRepository.findById(fromUserId).orElseThrow(() -> new Exception("User not found"))
        );
        ClientDto toUser = clientMapper.toDto(
                clientRepository.findById(dto.getToUserId()).orElseThrow(() -> new Exception("User not found"))
        );
        if (fromUser.getId().equals(toUser.getId())) throw new IllegalArgumentException("No puedes solicitarte a ti mismo");

        ExchangeRequestDto requestDto = exchangeRequestServiceImp.createExchangeRequest(
                bookOffered, bookRequested, fromUser, toUser
        );
        return ResponseEntity.ok(requestDto);
    }



    @PutMapping("/{id}/status")
    public ResponseEntity<ExchangeRequestDto> uptdateStatus(@PathVariable UUID id, @RequestParam ExchangeRequestStatus status) {
        return ResponseEntity.ok(exchangeRequestServiceImp.updateExchangeRequest(id, status));
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<ExchangeRequestDto> completeExchange(@PathVariable UUID id) {
        return ResponseEntity.ok(exchangeRequestServiceImp.CompleteExchangeRequest(id));
    }

    @GetMapping("/sent")
    public ResponseEntity<List<ExchangeRequestDto>> getSentRequests(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        final String prefix = "Bearer ";
        if (!authorizationHeader.startsWith(prefix)) {
            throw new RuntimeException("Header inválido");
        }
        String token = authorizationHeader.substring(prefix.length());
        UUID userId = jwtService.extractClientId(token);

        System.out.println("userId: " + userId);

        List<ExchangeRequestDto> result = exchangeRequestServiceImp.getSentExchangeRequest(userId);

        System.out.println("Exchange requests sent: " + (result != null ? result.size() : "null"));

        return ResponseEntity.ok(result);
    }


    @GetMapping("/received")
    public ResponseEntity<List<ExchangeRequestDto>> getReceivedRequests(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        String prefix = "Bearer ";
        if (!authorizationHeader.startsWith(prefix)) throw new RuntimeException("Header inválido");
        String token = authorizationHeader.substring(prefix.length());

        UUID userId = jwtService.extractClientId(token);
        return ResponseEntity.ok(exchangeRequestServiceImp.getRecivedExchangeRequests(userId));
    }





}
