package co.edu.unimagdalena.libros.libros.service;

import co.edu.unimagdalena.libros.libros.dto.CreateExchangeRequestDto;
import co.edu.unimagdalena.libros.libros.dto.ExchangeRequestDto;
import co.edu.unimagdalena.libros.libros.enums.ExchangeRequestStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExchangeRequestService {


    List<ExchangeRequestDto> getRecivedExchangeRequests(UUID userId);
    List<ExchangeRequestDto> getSentExchangeRequest(UUID userId);

    ExchangeRequestDto updateExchangeRequest(UUID id, ExchangeRequestStatus newStatus);
    ExchangeRequestDto completeExchangeRequest(UUID requestId);




}
