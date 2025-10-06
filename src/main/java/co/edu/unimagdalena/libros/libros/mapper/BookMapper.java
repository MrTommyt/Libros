package co.edu.unimagdalena.libros.libros.mapper;

import co.edu.unimagdalena.libros.libros.dto.BookDto;
import co.edu.unimagdalena.libros.libros.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(source = "bookDefinition", target = "bookDefinition")
    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "clientName", source = "client.name")
    @Mapping(target = "bookDefinitionID", source = "bookDefinition.id")
    BookDto toDto(Book book);

    Book toEntity(BookDto bookDto);
}


