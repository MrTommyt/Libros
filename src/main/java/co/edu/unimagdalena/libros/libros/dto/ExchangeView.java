package co.edu.unimagdalena.libros.libros.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ExchangeView(
        UUID id,
        String offeredTitle,
        String requestedTitle,
        String partnerName,
        LocalDateTime localDateTime
) {}

