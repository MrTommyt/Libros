package co.edu.unimagdalena.libros.libros.service;

import co.edu.unimagdalena.libros.libros.dto.ClientDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientService {
    Optional<ClientDto> getClientByName(String name);
    Optional<ClientDto> getClientByEmail(String email);
    Optional<ClientDto> getClientById(UUID id);

    List<ClientDto> getAllClients();
    ClientDto createClient(ClientDto ClientDto);
    void deleteClientById(UUID id);
    ClientDto updateClient(ClientDto ClientDto);
}
