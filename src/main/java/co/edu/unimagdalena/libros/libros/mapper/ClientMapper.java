package co.edu.unimagdalena.libros.libros.mapper;

import co.edu.unimagdalena.libros.libros.dto.ClientDto;
import co.edu.unimagdalena.libros.libros.entity.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDto toDto(Client client);
    Client toEntity(ClientDto Clientdto);
}
