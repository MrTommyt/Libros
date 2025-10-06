package co.edu.unimagdalena.libros.libros.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateExchangeRequestDto {
    private UUID bookRequestId;     // Este nombre, tal cual
    private UUID bookOfferedId;
    private UUID toUserId;

    private UUID id;
    private BookDto bookOffered;
    private BookDto bookRequested;
    private ClientDto fromUser;
    private ClientDto toUser;
    private String stateRequest;

}


