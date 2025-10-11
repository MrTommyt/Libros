package co.edu.unimagdalena.libros.libros.service.imp;

import co.edu.unimagdalena.libros.libros.dto.BookDto;
import co.edu.unimagdalena.libros.libros.dto.ClientDto;
import co.edu.unimagdalena.libros.libros.dto.ExchangeRequestDto;
import co.edu.unimagdalena.libros.libros.entity.Book;
import co.edu.unimagdalena.libros.libros.entity.Client;
import co.edu.unimagdalena.libros.libros.entity.ExchangeRequest;
import co.edu.unimagdalena.libros.libros.enums.ExchangeRequestStatus;
import co.edu.unimagdalena.libros.libros.exeption.BookNotFoundException;
import co.edu.unimagdalena.libros.libros.mapper.BookMapper;
import co.edu.unimagdalena.libros.libros.mapper.ExchangeRequestMapper;
import co.edu.unimagdalena.libros.libros.repository.BookRepository;
import co.edu.unimagdalena.libros.libros.repository.ExchangeRequestRespository;
import co.edu.unimagdalena.libros.libros.service.ExchangeRequestService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class ExchangeRequestServiceImp implements ExchangeRequestService {

    private final ExchangeRequestRespository exchangeRequestRespository;
    private final ExchangeRequestMapper exchangeRequestMapper;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public ExchangeRequestDto createExchangeRequest(BookDto bookOffered,BookDto bookRequested, ClientDto fromUser, ClientDto toUser) {
        ExchangeRequestDto exchangeRequestDto = new ExchangeRequestDto();

        exchangeRequestDto.setBookOffered(bookOffered);
        exchangeRequestDto.setBookRequested(bookRequested);
        exchangeRequestDto.setFromUser(fromUser);
        exchangeRequestDto.setToUser(toUser);

        exchangeRequestDto.setStatus(ExchangeRequestStatus.PENDING);
        exchangeRequestDto.setCreatedDate(LocalDateTime.now());

        return exchangeRequestMapper.toDto(exchangeRequestRespository
                .save(exchangeRequestMapper.toEntity(exchangeRequestDto)));

    }


    @Override
    public List<ExchangeRequestDto> getRecivedExchangeRequests(UUID userId) {
        return exchangeRequestRespository.findByToUserId(userId)
                .stream()
                .map(exchangeRequestMapper::toDto)
                .toList();
    }

    @Override
    public List<ExchangeRequestDto> getSentExchangeRequest(UUID userId) {
        return exchangeRequestRespository.findByFromUserId(userId)
                .stream()
                .map(exchangeRequestMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public ExchangeRequestDto updateExchangeRequest(UUID id, ExchangeRequestStatus newStatus) {
        if (newStatus == ExchangeRequestStatus.COMPLETED) {
            return CompleteExchangeRequest(id);
        }
        ExchangeRequest req = exchangeRequestRespository.findById(id).orElseThrow();
        req.setStatus(newStatus);
        return exchangeRequestMapper.toDto(exchangeRequestRespository.save(req));
    }

    @Override
    @Transactional
    public ExchangeRequestDto CompleteExchangeRequest(UUID requestId) {
        ExchangeRequest request = exchangeRequestRespository.findById(requestId)
                .orElseThrow(() -> new BookNotFoundException("Request not found"));

        Book bookRequested = bookRepository.findById(request.getBookRequested().getId())
                .orElseThrow(() -> new BookNotFoundException("Libro no encontrado"));
        Book bookOffered = bookRepository.findById(request.getBookOffered().getId())
                .orElseThrow(() -> new BookNotFoundException("Libro no encontrado"));

        bookRequested.setStateRequest("EXCHANGED");
        bookOffered.setStateRequest("EXCHANGED");
        bookRepository.saveAll(java.util.List.of(bookRequested, bookOffered));

        request.setStatus(ExchangeRequestStatus.COMPLETED);
        ExchangeRequest saved = exchangeRequestRespository.save(request);

        return exchangeRequestMapper.toDto(saved);
    }

}
