package co.edu.unimagdalena.libros.libros.service.imp;

import co.edu.unimagdalena.libros.libros.dto.ClientDto;
import co.edu.unimagdalena.libros.libros.mapper.ClientMapper;
import co.edu.unimagdalena.libros.libros.repository.ClientRepository;
import co.edu.unimagdalena.libros.libros.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientMapper clientMapper;
    private final ClientRepository clientRepository;

    @Override
    public Optional<ClientDto> getClientByName(String name) {
        return clientRepository.findByName(name).map(clientMapper::toDto);
    }

    @Override
    public Optional<ClientDto> getClientByEmail(String email) {
        return clientRepository.findByEmail(email).map(clientMapper::toDto);
    }

    @Override
    public Optional<ClientDto> getClientById(UUID id) {
        return clientRepository.findById(id).map(clientMapper::toDto);
    }

    @Override
    public ClientDto createClient(ClientDto ClientDto) {
        return clientMapper.toDto(clientRepository.save(clientMapper.toEntity(ClientDto)));
    }

    @Override
    public void deleteClientById(UUID id) {
        clientRepository.deleteById(id);
    }

    @Override
    public ClientDto updateClient(ClientDto ClientDto) {
        return clientMapper.toDto(clientRepository.save(clientMapper.toEntity(ClientDto)));
    }

    @Override
    public List<ClientDto> getAllClients() {
        return clientRepository.findAll().stream().map(clientMapper::toDto).toList();
    }
}
