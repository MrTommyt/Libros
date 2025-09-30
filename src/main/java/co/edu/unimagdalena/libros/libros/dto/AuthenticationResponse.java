package co.edu.unimagdalena.libros.libros.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    
    private String token;
    private String tokenType = "Bearer";
    private UUID userId;
    private String email;
    private String name;
}