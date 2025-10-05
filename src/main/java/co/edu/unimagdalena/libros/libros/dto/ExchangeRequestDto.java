package co.edu.unimagdalena.libros.libros.dto;

import co.edu.unimagdalena.libros.libros.enums.ExchangeRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRequestDto {
    private UUID id;
    private BookDto bookOffered;
    private BookDto bookRequested;
    private ClientDto fromUser;
    private ClientDto toUser;
    private ExchangeRequestStatus status; //
    private LocalDateTime createdDate;
}
