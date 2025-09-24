package co.edu.unimagdalena.libros.libros.exeption;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@Builder
public class ApiError {
    private LocalDateTime timestamp;
    private String message;
    private int status;
    private Map<String, String> errors;
}
