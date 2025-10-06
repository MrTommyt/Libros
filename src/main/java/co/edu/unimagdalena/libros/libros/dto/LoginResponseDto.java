package co.edu.unimagdalena.libros.libros.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    private String token;
    private String tokenType = "Bearer";
    private ClientDto user;

    public LoginResponseDto(String token, ClientDto user) {
        this.token = token;
        this.user = user;
    }
}
