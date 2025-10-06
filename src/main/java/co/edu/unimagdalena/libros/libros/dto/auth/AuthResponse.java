package co.edu.unimagdalena.libros.libros.dto.auth;

import java.util.UUID;

public record AuthResponse (String token, UUID userId) {
}
