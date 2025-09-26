package co.edu.unimagdalena.libros.libros.mapper;

import co.edu.unimagdalena.libros.libros.dto.BookDefinitionDto;
import co.edu.unimagdalena.libros.libros.entity.BookDefinition;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDefinitionDto toDto(BookDefinition bookDefinition);
    BookDefinition toEntity(BookDefinitionDto bookDefinitionDto);
}
