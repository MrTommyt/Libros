package co.edu.unimagdalena.libros.libros.service;

import co.edu.unimagdalena.libros.libros.dto.ClientDto;

import java.util.Optional;
import java.util.UUID;

public interface ClientService {
    Optional<ClientDto> getClientDtoByName(String name);
    Optional<ClientDto> getClientDtoByEmail(String email);
    Optional<ClientDto> getClientDtoById(UUID id);
    
    ClientDto createClientDto(ClientDto ClientDto);
    void deleteClientDtoById(UUID id);
    ClientDto updateClientDto(ClientDto ClientDto);
}
