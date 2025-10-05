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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class ExchangeRequestServiceImp implements ExchangeRequestService {

    private ExchangeRequestRespository exchangeRequestRespository;
    private ExchangeRequestMapper exchangeRequestMapper;
    private BookRepository bookRepository;
    private BookMapper bookMapper;

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
    public ExchangeRequestDto updateExchangeRequest(UUID id, ExchangeRequestStatus newStatus) {
        ExchangeRequest request = exchangeRequestRespository.findByid(id).orElseThrow();

        request.setStatus(newStatus);

        return exchangeRequestMapper.toDto(exchangeRequestRespository.save(request));

    }

    @Override
    public ExchangeRequestDto CompleteExchangeRequest(UUID requestId) {
        ExchangeRequest request = exchangeRequestRespository.findByid(requestId).orElseThrow(() -> new BookNotFoundException("Request not found"));

        request.getBookRequested().setStateRequest("COMPLETE");
        request.getBookOffered().setStateRequest("COMPLETE");


        bookRepository.save(request.getBookRequested());
        bookRepository.save(request.getBookOffered());

        request.setStatus(ExchangeRequestStatus.COMPLETED);

        return exchangeRequestMapper.toDto(exchangeRequestRespository.save(request));

    }

}
