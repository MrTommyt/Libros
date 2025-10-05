package co.edu.unimagdalena.libros.libros.mapper;

import co.edu.unimagdalena.libros.libros.dto.CreateExchangeRequestDto;
import co.edu.unimagdalena.libros.libros.dto.ExchangeRequestDto;
import co.edu.unimagdalena.libros.libros.entity.ExchangeRequest;

public interface ExchangeRequestMapper {
    ExchangeRequestDto toDto(ExchangeRequest exchangeRequest);
    ExchangeRequest toEntity(ExchangeRequestDto ExchangeRequestDto);
}
