package co.edu.unimagdalena.libros.libros.mapper;

import co.edu.unimagdalena.libros.libros.dto.BookDto;
import co.edu.unimagdalena.libros.libros.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mappings({
            @Mapping(source = "bookDefinition", target = "bookDefinition"),
            @Mapping(source = "bookDefinition.id", target = "bookDefinitionID"),
            @Mapping(source = "client.id", target = "clientId")
    })
    BookDto toDto(Book book);

    Book toEntity(BookDto bookDto);
}
