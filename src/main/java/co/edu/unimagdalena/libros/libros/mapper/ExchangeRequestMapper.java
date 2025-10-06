package co.edu.unimagdalena.libros.libros.mapper;

import co.edu.unimagdalena.libros.libros.dto.CreateExchangeRequestDto;
import co.edu.unimagdalena.libros.libros.dto.ExchangeRequestDto;
import co.edu.unimagdalena.libros.libros.entity.ExchangeRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {BookMapper.class, ClientMapper.class})
public interface ExchangeRequestMapper {
    ExchangeRequestDto toDto(ExchangeRequest exchangeRequest);
    ExchangeRequest toEntity(ExchangeRequestDto exchangeRequestDto);
}

