package co.edu.unimagdalena.libros.libros.controller;


import co.edu.unimagdalena.libros.libros.config.JwtUtil;
import co.edu.unimagdalena.libros.libros.dto.BookDto;
import co.edu.unimagdalena.libros.libros.dto.ClientDto;
import co.edu.unimagdalena.libros.libros.dto.CreateExchangeRequestDto;
import co.edu.unimagdalena.libros.libros.dto.ExchangeRequestDto;
import co.edu.unimagdalena.libros.libros.enums.ExchangeRequestStatus;
import co.edu.unimagdalena.libros.libros.mapper.BookMapper;
import co.edu.unimagdalena.libros.libros.mapper.ClientMapper;
import co.edu.unimagdalena.libros.libros.repository.BookRepository;
import co.edu.unimagdalena.libros.libros.repository.ClientRepository;
import co.edu.unimagdalena.libros.libros.service.imp.ExchangeRequestServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/exchange")
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
    private JwtUtil jwtUtil; // Tu servicio para extraer datos de JWT

    /**
     * Creates a new exchange request between two users for their books.
     * Endpoint: POST /exchange
     * Requires Authorization header (JWT).
     * @param dto Details of the exchange (offered/requested book, target user).
     * @param authorizationHeader JWT token for authentication.
     * @return The created ExchangeRequestDto.
     * @throws IllegalArgumentException if the user tries to exchange with themselves.
     */
    @PostMapping
    public ResponseEntity<ExchangeRequestDto> createExchange(
            @RequestBody CreateExchangeRequestDto dto,
            @RequestHeader("Authorization") String authorizationHeader
    )  {
        String token = authorizationHeader.substring("Bearer ".length());
        UUID fromUserId = jwtUtil.getIdFromJwtToken(token);

        BookDto bookOffered = bookMapper.toDto(bookRepository.findById(dto.getBookOfferedId()).orElseThrow());
        BookDto bookRequested = bookMapper.toDto(bookRepository.findById(dto.getBookRequestId()).orElseThrow());
        ClientDto fromUser = clientMapper.toDto(clientRepository.findById(fromUserId).orElseThrow());
        ClientDto toUser = clientMapper.toDto(clientRepository.findById(dto.getToUserId()).orElseThrow());

        if (fromUser.getId().equals(toUser.getId())) {
            throw new IllegalArgumentException("No puedes solicitar intercambio contigo mismo.");
        }

        ExchangeRequestDto requestDto = exchangeRequestServiceImp.createExchangeRequest(bookOffered, bookRequested, fromUser, toUser);
        return ResponseEntity.ok(requestDto);
    }

    /**
     * Updates the status of an existing exchange request.
     * Endpoint: PUT /exchange/{id}/status
     * @param id Exchange request ID.
     * @param status New status for the exchange request.
     * @return The updated ExchangeRequestDto.
     */
    @PutMapping("/{id}/status")
    public ResponseEntity<ExchangeRequestDto> uptdateStatus(@PathVariable UUID id, @RequestParam ExchangeRequestStatus status) {
        return ResponseEntity.ok(exchangeRequestServiceImp.updateExchangeRequest(id, status));
    }

    /**
     * Marks an exchange request as completed.
     * Endpoint: PUT /exchange/{id}/complete
     * @param id Exchange request ID.
     * @return The completed ExchangeRequestDto.
     */
    @PutMapping("/{id}/complete")
    public ResponseEntity<ExchangeRequestDto> completeExchange(@PathVariable UUID id) {
        return ResponseEntity.ok(exchangeRequestServiceImp.completeExchangeRequest(id));
    }

    /**
     * Retrieves all exchange requests sent by the authenticated user.
     * Endpoint: GET /exchange/sent
     * Requires Authorization header (JWT).
     * @param authorizationHeader JWT token for authentication.
     * @return List of sent ExchangeRequestDto.
     */
    @GetMapping("/sent")
    public ResponseEntity<List<ExchangeRequestDto>> getSentRequests(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        String token = authorizationHeader.substring("Bearer ".length());
        UUID userId = jwtUtil.getIdFromJwtToken(token);
        return ResponseEntity.ok(exchangeRequestServiceImp.getSentExchangeRequest(userId));
    }

    /**
     * Retrieves all exchange requests received by the authenticated user.
     * Endpoint: GET /exchange/received
     * Requires Authorization header (JWT).
     * @param authorizationHeader JWT token for authentication.
     * @return List of received ExchangeRequestDto.
     */
    @GetMapping("/received")
    public ResponseEntity<List<ExchangeRequestDto>> getReceivedRequests(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        String token = authorizationHeader.substring("Bearer ".length());
        UUID userId = jwtUtil.getIdFromJwtToken(token);
        return ResponseEntity.ok(exchangeRequestServiceImp.getRecivedExchangeRequests(userId));
    }





}
