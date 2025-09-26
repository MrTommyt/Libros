package co.edu.unimagdalena.libros.libros.service.imp;

import co.edu.unimagdalena.libros.libros.dto.ClientDto;
import co.edu.unimagdalena.libros.libros.mapper.ClientMapper;
import co.edu.unimagdalena.libros.libros.repository.ClientRepository;
import co.edu.unimagdalena.libros.libros.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientMapper clientMapper;
    private final ClientRepository clientRepository;

    @Override
    public Optional<ClientDto> getClientDtoByName(String name) {
        return clientRepository.findByName(name).map(clientMapper::toDto);
    }

    @Override
    public Optional<ClientDto> getClientDtoByEmail(String email) {
        return clientRepository.findByEmail(email).map(clientMapper::toDto);
    }

    @Override
    public Optional<ClientDto> getClientDtoById(UUID id) {
        return clientRepository.findById(id).map(clientMapper::toDto);
    }

    @Override
    public ClientDto createClientDto(ClientDto ClientDto) {
        return clientMapper.toDto(clientRepository.save(clientMapper.toEntity(ClientDto)));
    }

    @Override
    public void deleteClientDtoById(UUID id) {
        clientRepository.deleteById(id);
    }

    @Override
    public ClientDto updateClientDto(ClientDto ClientDto) {
        return clientMapper.toDto(clientRepository.save(clientMapper.toEntity(ClientDto)));
    }
}
