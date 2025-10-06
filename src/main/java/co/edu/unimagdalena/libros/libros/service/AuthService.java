package co.edu.unimagdalena.libros.libros.service;

import co.edu.unimagdalena.libros.libros.dto.LoginRequestDto;
import co.edu.unimagdalena.libros.libros.dto.LoginResponseDto;
import co.edu.unimagdalena.libros.libros.dto.RegisterRequestDto;
import co.edu.unimagdalena.libros.libros.dto.ClientDto;

public interface AuthService {
    LoginResponseDto login(LoginRequestDto loginRequest);
    ClientDto register(RegisterRequestDto registerRequest);
}
